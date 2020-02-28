package com.example.diethelperapp.DB

import androidx.room.*
import androidx.lifecycle.LiveData

@Dao
abstract class DietDAO {


    @Query("SELECT * FROM diet_table")
    abstract suspend fun getAllDiet(): List<Diet>

    @Query("SELECT id_diet FROM diet_table Where diet_name = :name_certain_diet")
    abstract suspend fun getIdCertainDiet(name_certain_diet:String): Int?

    @Query("SELECT id_diet FROM diet_table Where id_diet = :id_certain_diet")
    abstract suspend fun getNameCertainDiet(id_certain_diet:Int): Int?
//
    @Query("SELECT diet_name FROM diet_table")
    abstract suspend fun getAllNameDiets(): List<String>

    @Query("SELECT supporting_information  FROM diet_table Where diet_name = :id_certain_diet")
    abstract suspend fun getDescriptionCertainDiet(id_certain_diet:Int): String

    @Query("SELECT duration  FROM diet_table Where id_diet = :id_certain_diet")
    abstract suspend fun getDurationCertainDiet(id_certain_diet:Int): Int
//
//    // не верно, нужно исправить
//    @Transaction
//    @Query("SELECT * FROM dishes_table Where id_dishes = :name_certain_diet")
//    abstract suspend fun getDishesByCertainDiet(name_certain_diet:String): List<Diet>
//
    @Insert
    abstract suspend fun insert(diet: Diet)


    @Entity(tableName = "diet_table")
    class Diet {
        @PrimaryKey(autoGenerate = true)
        var id_diet: Int = 0
        var diet_name: String? = null
        var supporting_information: String? = null
        var duration: Int = 0
    }
    @Entity(tableName = "ingredients_table")
    class Ingredients {
        @PrimaryKey(autoGenerate = true)
        var id_ingred: Int = 0
        var ingred_name: String? = null
        var protein: Double? = null
        var fat: Double? = null
        var carbohydrates: Double? = null
        var calories: Double? = null
        var measure: String? = null
    }
    @Entity(tableName = "dishes_table")
    class Dishes {
        @PrimaryKey(autoGenerate = true)
        var id_dishes: Int = 0
        var dishes_name: String? = null
        var protein: Double? = null
        var fat: Double? = null
        var carbohydrates: Double? = null
        var calories: Double? = null
        var category: String? = null
        var mark: String? = null
        var description: String? = null
        var link_ingred: Int = 0
    }
    @Entity(tableName = "list_ingredients_table")
    class ListIngredients {

        var id_own_dishes: Int = 0
        var id_link_ingred: Int = 0
        var ingred_count: Int? = null
        var ingred_name: String? = null

    }
    @Entity(tableName = "tb_diet_dishes_MtoM", primaryKeys = ["id_own_diet","id_link_dishes"])
    class CrossRefDietOwnDishes {
        val id_own_diet: Int = 0
        val id_link_dishes: Int = 0
    }



}