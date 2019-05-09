package com.example.self_taught;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;

public class HomeFragmentFour extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.homemenufragfour, container, false);
        Button takePictureBtn = (Button) view.findViewById(R.id.HomeFragFourPictureBtn);
        Button uploadPictureBtn = (Button) view.findViewById(R.id.HomeFragFourUploadBtn);
        takePictureBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(getActivity(), TakePicture.class);
                startActivity(pictureIntent);
            }
        });
        return view;
    }
}
