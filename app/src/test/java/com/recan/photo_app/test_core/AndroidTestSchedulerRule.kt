package com.recan.photo_app.test_core

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AndroidTestSchedulerRule : TestRule {

    val mainThreadScheduler: Scheduler = Schedulers.trampoline()
    val ioScheduler: Scheduler = Schedulers.trampoline()

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { ioScheduler }
                RxAndroidPlugins.setMainThreadSchedulerHandler { mainThreadScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { mainThreadScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}