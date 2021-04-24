package com.recan.photo_app.presentation.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.recan.photo_app.R
import javax.inject.Inject

open class BaseActivity<V : PresentationView, P : BasePresenter<V>> : AppCompatActivity(),
    PresentationView {

    @Inject
    protected lateinit var presenterFactory: PresenterFactory<V, P>

    private val errorToast: Toast by lazy {
        Toast.makeText(this, "", Toast.LENGTH_LONG)
    }

    protected lateinit var presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter =
            runCatching { lastNonConfigurationInstance as P }.getOrDefault(presenterFactory.get())
    }

    override fun onStart() {
        super.onStart()
        bindPresenter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bindPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    private fun bindPresenter() {
        try {
            presenter.takeUnless { it.isBinned }?.bindView(this as V)
        } catch (e: ClassCastException) {
            throw RuntimeException(
                "The `view` provided does not implement the PresentationView interface " +
                        "expected by " + presenter.javaClass.simpleName + ".", e
            )
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any = presenter

    override fun showError(message: String?) {
        if (message != null) errorToast.setText(message)
        else errorToast.setText(R.string.base_error)
        errorToast.show()
    }

}