package com.example.morozovhints.L101.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.morozovhints.L101.domain.ShopItem
import com.example.morozovhints.L101.domain.ShopListRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

/**
 * Синглтон реализации репозитория Domain слоя.
 * Наследует интерфейс по работе с данным Domain слоя.
 * Переопределяет методы репозитория Domain слоя, реализуя методы.
 */
object ShopListRepositoryImpl : ShopListRepository {

    @SuppressLint("StaticFieldLeak")
    private var db = Firebase.firestore
    private var shopList = mutableListOf<ShopItem>()
    private var idIncrement = 0
    private var shopListLiveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 20) {
            val item = ShopItem(name = "name $i", count = i, enabled = Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = idIncrement++
        }
        db.collection("shopItems").document("${shopItem.id}")
            .set(shopItem)
            .addOnSuccessListener { documentReference ->
                Log.d("Firecloud", "DocumentSnapshot added with ID: ${shopItem.id}")
                getShopList()
            }
            .addOnFailureListener { e ->
                Log.w("Firecloud", "Error adding document", e)
            }
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        db.collection("shopItems").document(shopItem.id.toString())
            .delete()
            .addOnSuccessListener {
                Log.d("Firecloud", "DocumentSnapshot successfully deleted!")
                getShopList()
            }
            .addOnFailureListener { e -> Log.w("Firecloud", "Error deleting document", e) }
    }

    override fun editShopItem(shopItem: ShopItem) {
        val washingtonRef = db.collection("shopItems").document(shopItem.id.toString())

        washingtonRef.update("name", shopItem.name)
        washingtonRef.update("count", shopItem.count)
        washingtonRef.update("enabled", shopItem.enabled)
            .addOnSuccessListener {
                Log.d("Firecloud", "DocumentSnapshot successfully updated!")
                getShopList()
            }
            .addOnFailureListener { e -> Log.w("Firecloud", "Error updating document", e) }

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        var shopItemFromFB: ShopItem? = null
        db.collection("shopItems").document(shopItemId.toString())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                shopItemFromFB = documentSnapshot.toObject<ShopItem>()
            }
        return shopItemFromFB ?: ShopItem()
        //throw RuntimeException()

        //TODO() Проблема асинхронной загрузки. Решить после изучения корутинов.
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        db.collection("shopItems")
            .get()
            .addOnSuccessListener { result ->
                shopList = result.toObjects(ShopItem::class.java)
                    .sortedBy { it.id } as MutableList<ShopItem>
                updateList()
                Log.d("Firecloud", "ShopList: ${shopList.joinToString()}")
                Log.d("Firecloud", "shopListLiveData: ${shopListLiveData.value}")
            }
            .addOnFailureListener { exception ->
                Log.w("Firecloud", "Error getting documents.", exception)
            }
        return shopListLiveData
    }

    private fun updateList() {
        shopListLiveData.value = shopList.toList()
    }
}