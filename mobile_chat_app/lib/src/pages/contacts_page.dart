import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/provider/socket_provider.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
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

    Provider.of<UserProvider>(context, listen: false).getContacts();
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
                SocketProvider socket = Provider.of<SocketProvider>(context, listen: false);
                socket.disconnectStomp();

                Navigator.pushReplacementNamed(context, 'login');
              },
            ),
          )
        ],
      ),
      body: SafeArea(
        child: Consumer<UserProvider>(
          builder: (context, data, child){
            var userList = data.userList;

            if(userList.isNotEmpty){
              return ListView.separated(
                shrinkWrap: true,
                physics: const BouncingScrollPhysics(),
                itemCount: userList.length,
                separatorBuilder: (context, i) => const Divider(), 
                itemBuilder: (context, i) =>  ListTile(
                  title: Text(userList[i].name),
                  leading: CircleAvatar(
                    backgroundColor: Colors.blue[100],
                    child: Text(userList[i].name.substring(0,2)),
                  ),
                  trailing: Container(
                    width: 10,
                    height: 10,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(100),
                      color: Colors.red
                    ),
                  ),
                  onTap: () { 
                    SocketProvider socket = Provider.of<SocketProvider>(context, listen: false);
                    //socket.subscribeQueue(userList[i].uuid);
                    
                    Navigator.pushNamed(context, 'chat', arguments: userList[i]);
                  }
                ),
              );              
            }else{
              return Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: const [
                    CircularProgressIndicator(),
                    SizedBox(height: 20),
                    Text('Cargando usuarios!!')
                  ],
                ),
              );
            }
          },
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