package com.example.common.common.presentation.schedulers

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler

    fun mainThread(): Scheduler
}
