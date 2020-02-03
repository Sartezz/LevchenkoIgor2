package com.example.cameraandpermission

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.zxing.Result
import kotlinx.android.synthetic.main.fragment_blank.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BlankFragment : Fragment(), ZXingScannerView.ResultHandler {


    private lateinit var scannerView: ZXingScannerView
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scannerView = ZXingScannerView(context)
        scannerView.setResultHandler(this)
        txtResult = text_view
    }

    override fun handleResult(rawResult: Result?) {
        txtResult.text = rawResult?.text ?: "Empty"
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}
