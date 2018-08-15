package com.zygne.circlepageindicator.util;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Bardur Thomsen on 8/15/18.
 */
public class CirclePagerIndicator extends RecyclerView.ItemDecoration {


    private int colorActive = 0xFFFFFFFF;
    private int colorInactive = 0x66FFFFFF;

    private static final float DP = Resources.getSystem().getDisplayMetrics().density;

    private final float padding = DP * 4;

    private final float radius = DP * 5;

    private final float radiusActive = DP * 6;

    private final float diameter = (radiusActive * 2);

    private final Paint paint = new Paint();

    public CirclePagerIndicator() {
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    public void setColors(int colorActive, int colorInactive) {
        this.colorActive = colorActive;
        this.colorInactive = colorInactive;
    }

    @Override
    public void onDrawOver(@NotNull Canvas c, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int itemCount = parent.getAdapter().getItemCount();

        // center horizontally, calculate width and subtract half from center
        float totalLength = diameter * itemCount;
        float paddingBetweenItems = Math.max(0, itemCount - 1) * padding;
        float indicatorTotalWidth = totalLength + paddingBetweenItems;
        float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;

        // center vertically in the allotted space
        float indicatorPosY = parent.getHeight() - radiusActive;

        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount);

        // find active page (which should be highlighted)
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();

        int activePosition = layoutManager.findFirstVisibleItemPosition();

        if (activePosition == RecyclerView.NO_POSITION) {
            return;
        }

        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, itemCount);
    }

    private void drawInactiveIndicators(Canvas c, float startX, float indicatorPosY, int itemCount) {
        paint.setColor(colorInactive);

        final float itemWidth = diameter + padding;

        float start = startX;

        for (int i = 0; i < itemCount; i++) {
            float cx = start + radius;
            float cy = indicatorPosY;
            c.drawCircle(cx, cy, radius, paint);
            start += itemWidth;
        }
    }

    private void drawHighlights(Canvas c, float startX, float posY, int highlightPosition, int itemCount) {
        paint.setColor(colorActive);

        final float itemWidth = diameter + padding;

        for (int i = 0; i < itemCount; i++) {
            float highlightStart = startX + itemWidth * highlightPosition;
            float cx = highlightStart + radiusActive;
            float cy = posY;
            c.drawCircle(cx, cy, radiusActive, paint);
        }

    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = (int) radius *2;
    }

}
