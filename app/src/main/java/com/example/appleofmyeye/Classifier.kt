package com.example.appleofmyeye

import android.graphics.Bitmap
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils


class Classifier(modelPath: String?) {
    var model: Module = Module.load(modelPath)
    var mean = floatArrayOf(0.485f, 0.456f, 0.406f)
    var std = floatArrayOf(0.229f, 0.224f, 0.225f)

    fun preprocess(bitmap: Bitmap?, size: Int): Tensor {
        var b = Bitmap.createScaledBitmap(bitmap!!, size, size, true)
        return TensorImageUtils.bitmapToFloat32Tensor(b, mean, std)
    }

    fun argMax(inputs: FloatArray): Int {
        var maxIndex = -1
        var maxvalue = 0.0f
        for (i in inputs.indices) {
            if (inputs[i] > maxvalue) {
                maxIndex = i
                maxvalue = inputs[i]
            }
        }
        return maxIndex
    }

    fun predict(bitmap: Bitmap?): String {
        val tensor = preprocess(bitmap, 100)
        val inputs = IValue.from(tensor)
        val outputs = model.forward(inputs).toTensor()
        val scores = outputs.dataAsFloatArray
        val classIndex = argMax(scores)
        return Constants.IMAGENET_CLASSES[classIndex]
    }

}