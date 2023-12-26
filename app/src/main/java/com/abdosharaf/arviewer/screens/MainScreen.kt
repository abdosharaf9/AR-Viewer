package com.abdosharaf.arviewer.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdosharaf.arviewer.R
import com.abdosharaf.arviewer.ui.theme.MainColor
import com.abdosharaf.arviewer.ui.theme.SecondColor
import com.google.ar.core.Config
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode

@Preview
@Composable
fun MainTest() {
    MainScreen(model = "plant_in_pot") {}
}

@Composable
fun MainScreen(model: String, onButtonClicked: () -> Unit) {
    val modelNode = remember {
        mutableStateOf<ArModelNode?>(null)
    }

    var isAnchor by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ARSceneView(modelNode, model)

        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .clickable {
                        if (modelNode.value != null) {
                            isAnchor = !isAnchor
                            if (isAnchor) {
                                modelNode.value!!.anchor()
                            } else {
                                modelNode.value!!.detachAnchor()
                            }
                        }
                    }
                    .background(MainColor)
                    .padding(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrows),
                    contentDescription = if (isAnchor) stringResource(R.string.unpin_the_model)
                    else stringResource(R.string.pin_the_model),
                    tint = if (isAnchor) SecondColor else Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .clickable { onButtonClicked() }
                    .background(MainColor)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = stringResource(R.string.choose_a_model),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
private fun ARSceneView(modelNode: MutableState<ArModelNode?>, model: String) {
    var arSceneView: ArSceneView

    LaunchedEffect(key1 = model) {
        modelNode.value?.loadModelGlbAsync(
            glbFileLocation = "models/${model}.glb",
            scaleToUnits = 0.8f
        )
        Log.e("````TAG````", "MainScreen: loading the model, $model")
    }

    ARScene(
        modifier = Modifier.fillMaxSize(),
        planeRenderer = false,
        onCreate = { scene ->
            arSceneView = scene
            arSceneView.lightEstimationMode = Config.LightEstimationMode.DISABLED
            arSceneView.planeRenderer.isShadowReceiver = false
            modelNode.value = ArModelNode(
                arSceneView.engine,
                PlacementMode.INSTANT
            ).apply {
                loadModelGlbAsync(
                    glbFileLocation = "models/${model}.glb",
                    scaleToUnits = 0.8f,
                    autoAnimate = true
                )
            }
            arSceneView.addChild(modelNode.value!!)
        }
    )
}