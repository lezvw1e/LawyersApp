package com.example.lawyersapp.data.lawyers.repository

import com.example.lawyersapp.data.lawyers.dto.LawyersDto
import com.example.lawyersapp.data.utils.mapper.LawyersDtoToEntity
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import com.example.lawyersapp.domain.lawyers.repository.LawyersRepository
import com.example.lawyersapp.core.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LawyersRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : LawyersRepository {


    private val lawyersDtoToEntity = LawyersDtoToEntity()
    override suspend fun getLawyers(lawyers: String): Flow<BaseResult<List<LawyersEntity>, String>> = callbackFlow {
        db.collection(lawyers).get().addOnSuccessListener { task  ->
            val list: MutableList<LawyersDto> = ArrayList()
            for (d in task.documents){
                val model = d.toObject(LawyersDto::class.java)?: LawyersDto()
                model.id = d.id
                list.add(model)
            }
            val temp = lawyersDtoToEntity.fromToList(list)
            temp?.let { trySend(SuccessResult(it)) }
        }.addOnFailureListener {
            trySend(ErrorResult(it.message))
        }
        awaitClose {
            trySend(PendingResult)
        }
    }
}