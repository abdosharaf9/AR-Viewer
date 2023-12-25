package com.abdosharaf.arviewer

import androidx.annotation.DrawableRes

data class ModelItem(
    val name: String,
    val path: String,
    @DrawableRes val image: Int
)

val models = listOf(
    ModelItem(
        name = "Front Body Anatomy",
        path = "front_body_anatomy",
        image = R.drawable.front_body_anatomy
    ),
    ModelItem(
        name = "Male Human Skeleton",
        path = "male_human_skeleton_-_zbrush_-_anatomy_study",
        image = R.drawable.male_human_skeleton
    ),
    ModelItem(
        name = "Anubis",
        path = "anubis_textured",
        image = R.drawable.anubis
    ),
    ModelItem(
        name = "Plant in pot",
        path = "plant_in_pot",
        image = R.drawable.plant_in_pot
    ),
    ModelItem(
        name = "JBL Charge 3 speaker",
        path = "jbl_charge_3_speaker",
        image = R.drawable.jbl_charge_3_speaker
    ),
    ModelItem(
        name = "Sofa - IKEA Nockby",
        path = "sofa_-_ikea_nockeby",
        image = R.drawable.sofa_ikea_nockeby
    ),
    ModelItem(
        name = "IKEA Linnmon/Alex Desk",
        path = "ikea_linnmonalex_desk",
        image = R.drawable.ikea_linnmon_alex_desk
    ),
    ModelItem(
        name = "IKEA ALEX Drawer Unit",
        path = "ikea_alex_drawer_unit",
        image = R.drawable.ikea_alex_drawer_unit
    ),
    ModelItem(
        name = "Tesla Model X",
        path = "tesla_model_x",
        image = R.drawable.tesla_model_x
    )
)