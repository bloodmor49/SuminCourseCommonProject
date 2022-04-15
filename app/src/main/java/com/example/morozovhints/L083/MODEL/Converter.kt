package com.example.morozovhints.L083.MODEL

import androidx.room.TypeConverter
import com.example.morozovhints.L083.MODEL.pojo.Specialty
import com.google.gson.Gson

/**
 * Преобразование списка JSON в список с помощью GSON.
 */
class Converter {

    @TypeConverter
    fun listSpecialtyToString(listOfSpecialty: List<Specialty>): String =
        Gson().toJson(listOfSpecialty)


    @TypeConverter
    fun stringsToListSpecialty(stringSpecialty: String): List<Specialty> {
        val gson = Gson()
        val objects = gson.fromJson(stringSpecialty, ArrayList::class.java)
        val specialties: ArrayList<Specialty> = arrayListOf()
        for (obj in objects) {
            specialties.add(gson.fromJson(obj.toString(), Specialty::class.java))
        }
        return specialties
    }
}


///**
// * Преобразование списка JSON в список (устаревший метод, без использования GSON)
// */
//private class ConverterOld {
//    fun listSpecialtyToStringOld(listOfSpecialty:List<Specialty>): String {
//        val jsonArray = JSONArray()
//        for (specialty in listOfSpecialty){
//            val jsonObject = JSONObject()
//            try {
//                jsonObject.put("id",specialty.specialtyId)
//                jsonObject.put("name",specialty.name)
//                jsonArray.put(jsonObject)
//            } catch (e:Exception) {e.printStackTrace()}
//        }
//        return jsonArray.toString()
//    }
//}