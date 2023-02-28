package com.example.lawyersapp.data.services.repository

import com.example.lawyersapp.core.*
import com.example.lawyersapp.data.services.dto.ServicesDto
import com.example.lawyersapp.data.utils.mapper.ServicesDtoToEntity
import com.example.lawyersapp.domain.services.entity.ServicesEntity
import com.example.lawyersapp.domain.services.repository.ServicesRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): ServicesRepository {


    private val servicesDtoToEntity = ServicesDtoToEntity()
    override fun getServices(language: String): Flow<BaseResult<List<ServicesEntity>, String>> = callbackFlow {
        db.collection(language).get().addOnSuccessListener { task ->
            val list: MutableList<ServicesDto> = ArrayList()
            for (d in task.documents){
                val model = d.toObject(ServicesDto::class.java)?: ServicesDto()
                model.id = d.id
                list.add(model)
            }
            val temp = servicesDtoToEntity.fromToList(list)
            temp?.let { trySend(SuccessResult(it)) }
        }.addOnFailureListener {
            trySend(ErrorResult(it.message))
        }
        awaitClose {
            trySend(PendingResult)
        }
    }
}