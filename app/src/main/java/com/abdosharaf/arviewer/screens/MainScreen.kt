package com.abdosharaf.arviewer.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdosharaf.arviewer.R
import com.abdosharaf.arviewer.ui.theme.MainColor
import com.google.ar.core.Config
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.PlacementMode

@Preview
@Composable
fun MainTest() {
    MainScreen(model = "plant_in_pot") {}
}

@Composable
fun MainScreen(model: String, onButtonClicked: () -> Unit) {
    val nodes = remember {
        mutableListOf<ArNode>()
    }
    val modelNode = remember {
        mutableStateOf<ArModelNode?>(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ARSceneView(nodes, modelNode, model)

        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(60.dp)
                .clip(CircleShape)
                .clickable { onButtonClicked() }
                .background(MainColor)
                .padding(14.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_button),
                contentDescription = "Choose a Model",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun ARSceneView(
    nodes: MutableList<ArNode>,
    modelNode: MutableState<ArModelNode?>,
    model: String
) {
    LaunchedEffect(key1 = model) {
        modelNode.value?.loadModelGlbAsync(
            glbFileLocation = "models/${model}.glb",
            scaleToUnits = 0.8f
        )
        Log.e("````TAG````", "MainScreen: loading the model, $model")
    }

    ARScene(
        modifier = Modifier.fillMaxSize(),
        nodes = nodes,
        planeRenderer = true,
        onCreate = { arSceneView ->
            arSceneView.lightEstimationMode = Config.LightEstimationMode.DISABLED
            arSceneView.planeRenderer.isShadowReceiver = false
            modelNode.value = ArModelNode(
                arSceneView.engine,
                PlacementMode.INSTANT,
                instantAnchor = true
            ).apply {
                loadModelGlbAsync(
                    glbFileLocation = "models/${model}.glb",
                    autoAnimate = true
                )
            }
            nodes.add(modelNode.value!!)
        },
        onSessionCreate = {
            planeRenderer.isVisible = false
        }
    )
}