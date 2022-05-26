package com.example.reddit

import com.example.common.common.presentation.schedulers.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestingSchedulersProvider : SchedulersProvider {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun mainThread(): Scheduler = Schedulers.trampoline()
}