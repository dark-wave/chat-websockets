import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/dto/user.dart';
import 'package:mobile_chat_app/src/dto/user_login_response.dart';
import 'package:mobile_chat_app/src/provider/contacts_provider.dart';
import 'package:mobile_chat_app/src/provider/login_provider.dart';
import 'package:mobile_chat_app/src/provider/socket_provider.dart';
import 'package:mobile_chat_app/src/widgets/add_contact_dialog.dart';
import 'package:provider/provider.dart';

class ContactsPage extends StatefulWidget {
  const ContactsPage({Key? key}) : super(key: key);

  @override
  State<ContactsPage> createState() => _ContactsPageState();
}

class _ContactsPageState extends State<ContactsPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Contactos'),
        actions:[
          Padding(
            padding: const EdgeInsets.only(right: 10),
            child: IconButton(
              icon: const Icon(Icons.logout),
              onPressed: () async{
                UserLoginResponse logedUser = Provider.of<LoginProvider>(context, listen: false).userLoginResponse;
                bool logoutResponse = await Provider.of<LoginProvider>(context, listen: false).logout(logedUser.uuid);
            
                if(logoutResponse){  
                  Future.microtask(() => Provider.of<ContactsProvider>(context, listen: false).clearContacts());
                  Future.microtask(() => Provider.of<SocketProvider>(context, listen: false).disconnectStomp());
                  Future.microtask(() => Navigator.pushReplacementNamed(context, 'login'));
                }                
              },
            ),
          )
        ],
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Consumer<SocketProvider>(
            builder: (context, data, child){
              var contactList = data.contactList;
        
              if(contactList.isNotEmpty){
                return ListView.separated(
                  shrinkWrap: true,
                  physics: const BouncingScrollPhysics(),
                  itemCount: contactList.length,
                  separatorBuilder: (context, i) => const Divider(), 
                  itemBuilder: (context, i) =>  ListTile(
                    title: Text(contactList[i].name),
                    leading: CircleAvatar(
                      backgroundColor: Colors.blue[100],
                      child: Text(contactList[i].name.substring(0,2)),
                    ),
                    trailing: Container(
                      width: 10,
                      height: 10,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(100),
                        color: contactList[i].connected ? Colors.green : Colors.red
                      ),
                    ),
                    onTap: () { 
                      SocketProvider socket = Provider.of<SocketProvider>(context, listen: false);
                      LoginProvider login = Provider.of<LoginProvider>(context, listen: false);
        
                      User user = User(
                        uuid: contactList[i].uuid,
                        name: contactList[i].name,
                        connected: true
                      );
        
                      //Limpiamos los mensajes anteriores
                      socket.clearMessageList();
        
                      //Cargamos el historico de mensjes del usuario
                      socket.loadLastMessages(login.userLoginResponse.uuid, user.uuid);
                      
                      Navigator.pushReplacementNamed(context, 'chat', arguments: user);
                    }
                  ),
                );
              }else{
                return const Center(
                  child: Text('No tienes contactos agregados')
                );
              }
            },
          ),
        )
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () => showDialog(
          context: context,
          builder: (context) => const AddContactDialog()
        )
      )
    );
  }
}