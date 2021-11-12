package com.example.restaurant.common.presentationLayer.rx.schedulers

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler

    fun mainThread(): Scheduler
}
