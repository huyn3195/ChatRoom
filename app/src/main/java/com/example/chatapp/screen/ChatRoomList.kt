package com.example.chatapp.screen


import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapp.data.Room
import com.example.chatapp.viewmodel.RoomViewModel
import androidx.compose.runtime.getValue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomListScreen(
    roomViewModel: RoomViewModel = viewModel(),
    onJoinClicked: (Room) -> Unit
){
    val rooms by roomViewModel.rooms.observeAsState(emptyList())
    val showDialog = remember { mutableStateOf(false) }
    val roomName = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text= "Chat Room",fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(rooms){ room ->
                RoomItem(room= room,onJoinClicked={onJoinClicked(room)})
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            showDialog.value= true

        },
                modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "Create Room")

           if(showDialog.value){
               AlertDialog(onDismissRequest = {showDialog.value=false},
                   title = {Text(text = "Create Room")},
                   text = {
                       OutlinedTextField(
                           value = roomName.value,
                           onValueChange = {roomName.value=it},
                           label = {Text("Room Name")}
                       )
                   },
                   confirmButton = {
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp)
                        , horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(onClick = {
                            if(roomName.value.isNotBlank()){
                                showDialog.value=false
                                roomViewModel.createRoom(name = roomName.value)
                            }


                        }) {
                            Text("Add")
                        }
                        Button(onClick = {
                            showDialog.value=false
                        }) {
                            Text("Cancel")
                        }
                    }
                   }
                   )
           }
        }

    }
}
@Composable
fun RoomItem(
    room: Room,
    onJoinClicked: (Room) -> Unit
){
    Row (modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Text(text= room.name,fontSize = 16.sp, fontWeight = FontWeight.Normal)
        OutlinedButton(
            onClick = {
                onJoinClicked(room)
            }
        ) {
            Text("Join")
        }
    }

}

