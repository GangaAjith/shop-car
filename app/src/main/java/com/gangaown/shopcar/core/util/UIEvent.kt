package com.gangaown.shopcar.core.util

sealed class UIEvent{
    data class ShowSnackBar (val message:String): UIEvent()
}
