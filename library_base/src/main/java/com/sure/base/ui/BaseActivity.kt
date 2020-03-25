package com.sure.base.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * author pisa
 * date  2020/3/23
 * version 1.0
 * effect :activity的基类
 */
class BaseActivity<IViewBinding : ViewBinding, IViewModel : ViewModel> : AppCompatActivity() {
//    protected var viewBinding: IViewBinding? = null
//
//    protected fun createViewBind(): IViewBinding? {
//        val pt: ParameterizedType? = try {
//            this.javaClass.genericSuperclass as ParameterizedType?
//        } catch (e: Exception) {
//            return null
//        }
//        if (pt != null) {
//            for (type in pt.actualTypeArguments) {
//                try {
//                    if (viewBind != null) {
//                        break
//                    }
//                    val clazz: Class<ViewBind> =
//                        pt.actualTypeArguments[0] as Class<ViewBind>
//                    val inflate = clazz.getMethod(
//                        "inflate",
//                        LayoutInflater::class.java
//                    )
//                    viewBind = inflate.invoke(null, layoutInflater) as ViewBind
//                } catch (e: InvocationTargetException) {
//                    Timber.e(e.targetException)
//                } catch (e: Exception) {
//                    Timber.e(e)
//                }
//            }
//        }
//        return viewBind
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}