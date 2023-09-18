package com.ayoba.domain.base

import com.ayoba.data.core.result.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params>() {
    abstract suspend fun run(param: Params): Result<Type>

    abstract suspend fun runFlow(param: Params): Flow<Result<Type>>
    operator fun invoke(
        viewModelScope: CoroutineScope,
        params: Params,
        onResult: (Result<Type>) -> Unit
    ) {
//        viewModelScope.launch {
//            CoroutineScope(Dispatchers.IO)
//                .launch {
//                    val result = run(params)
//                    withContext(Dispatchers.Main)
//                    {
//                        onResult(result)
//                    }
//                }
//        }

//        flow API
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
            val result = runFlow(params)
//                    withContext(Dispatchers.Main)
//                    {
            result.collect { onResult(it) }
//                    }
                }
        }
    }

    class None
}