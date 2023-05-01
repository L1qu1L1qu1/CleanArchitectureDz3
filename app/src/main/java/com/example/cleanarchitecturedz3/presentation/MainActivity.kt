package com.example.cleanarchitecturedz3.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecturedz3.ui.theme.CleanArchitectureDz3Theme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecturedz3.domain.House

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNavigation()

        }
    }
}
@Composable
fun MyFirstScreen(myViewModel: MyViewModel = hiltViewModel(), navController: NavController){
    myViewModel.getHouses()
    when(myViewModel.myMutableState.value) {
        is ViewModelState.Loading ->{}
        is ViewModelState.Sucsess ->{}
        is ViewModelState.Error ->{}
        else -> {}
    }
    LazyColumn(modifier = Modifier.padding(10.dp)){
        items(myViewModel.myMutable){
            Text(text = it.name, fontSize = 25.sp
                 , modifier = Modifier.clickable { navController.navigate("mySecondScreen/${it.name}") })
        }
    }
}
@Composable
fun MySecondScreen(name: String, navController: NavController){
    Text(text = name, fontSize = 25.sp)
    Button(onClick = { navController.navigate("myFirstScreen") }) {

    }
}
@Composable
fun MyNavigation(){
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "myFirstScreen"){
        composable("myFirstScreen"){ MyFirstScreen(navController = navController)}
        composable("mySecondScreen/{name}"){
            var name:String = it.arguments?.getString("name")?:""
            MySecondScreen(name = name, navController = navController)}
    }

}
