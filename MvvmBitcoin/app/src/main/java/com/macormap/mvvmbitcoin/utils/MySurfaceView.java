package com.macormap.mvvmbitcoin.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import com.macormap.mvvmbitcoin.db.entities.IntradayEntity;

import java.util.ArrayList;
import java.util.List;


/** to design the graph of intrady stock using the list of
 *  IntradayEntity.
 */


public class MySurfaceView extends SurfaceView {

    public Paint mPaint;
    public Path path;
    private Canvas canvas = null;
    private ArrayList<PathWithPaint> laPolilinea = new ArrayList<PathWithPaint>();

    private boolean iniziato = false;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();

        this.setBackgroundColor(Color.BLACK);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(2);
    }

    private class PathWithPaint {
        private Path path;
        public Path getPath() {
            return path;
        }
        public void setPath(Path path) {
            this.path = path;
        }
        private Paint mPaint;
        public Paint getmPaint() {
            return mPaint;
        }
        public void setmPaint(Paint mPaint) {
            this.mPaint = mPaint;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!iniziato) { iniziato = true; this.canvas = canvas; return; }
        if (laPolilinea.size() > 0) {
            canvas.drawPath(laPolilinea.get(laPolilinea.size() - 1).getPath(),
                    laPolilinea.get(laPolilinea.size() - 1).getmPaint());
        }
    }

    /**  Update the polyline with the new data  */

    public void aggiornaPath(List<IntradayEntity> rowStock) {
        laPolilinea.clear();
        IntradayEntity stockIntradayEntity;
        PathWithPaint pp = new PathWithPaint();
        double minvalue;
        double maxvalue;

        if (rowStock.size()>0) {
            stockIntradayEntity = rowStock.get(0);
            minvalue = stockIntradayEntity.getValueStock();
            maxvalue = stockIntradayEntity.getValueStock();
        }
        else return;

        for (int i=1; i<rowStock.size(); i++ ) {
            stockIntradayEntity = rowStock.get(i);
            if (minvalue>stockIntradayEntity.getValueStock()) { minvalue = stockIntradayEntity.getValueStock();}
            if (maxvalue<stockIntradayEntity.getValueStock()) { maxvalue = stockIntradayEntity.getValueStock();}
        }

        double DeltaminMax = maxvalue - minvalue;
        if (canvas == null) {return;}

        double dimxImg     =  canvas.getWidth();
        double dimyImg     =  canvas.getHeight();
        double x1,y1;
        int x1I,y1I;
        double stepPtx;
        int bordoX2 = (int) dimxImg/10;
        int bordoX  = (int) bordoX2/2;
        int bordoY2 = (int) dimyImg/10;
        int bordoY  = (int) bordoY2/2;

        double scalay  =  (dimyImg-bordoY2) / DeltaminMax;

        if  (rowStock.size()>0) {
            stepPtx =  ( ((dimxImg- bordoX2)*1.0d) / rowStock.size()  );
            stockIntradayEntity = rowStock.get(0);
            x1 = bordoX;
            y1 = bordoY + (maxvalue - stockIntradayEntity.getValueStock()) * scalay;
            x1I = (int) Math.round(x1);
            y1I = (int) Math.round(y1);
            path.moveTo(x1I, y1I);

            for (int i = 1; i < rowStock.size(); i++) {
                stockIntradayEntity = rowStock.get(i);
                x1 = bordoX + (i* stepPtx);
                y1 = bordoY + (maxvalue - stockIntradayEntity.getValueStock()) * scalay;
                x1I = (int) Math.round(x1);
                y1I = (int) Math.round(y1);
                path.lineTo(x1I, y1I);
            }
            pp.setPath(path);
            pp.setmPaint(mPaint);
            laPolilinea.add(pp);
        }

        invalidate();
    }







}