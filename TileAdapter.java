package com.example.galzilca.battleship;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TileAdapter extends BaseAdapter {

    private Context mContext;
    private Board mBoard;
    private boolean isPlayerBoard;

    public TileAdapter(Context context, Board board, boolean isPlayer) {
        mBoard = board;
        mContext = context;
        isPlayerBoard = isPlayer;
    }

    @Override
    public int getCount() {
        return mBoard.getBoardSize();
    }

    @Override
    public Object getItem(int position) {
        return mBoard.getTile(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TileView tileView;
        if (convertView == null) {
            Log.e("Tile Adapter", "Not RECYCLED");
            tileView = new TileView(mContext);
        } else {
            tileView = (TileView) convertView;
            Log.e("Tile Adapter", "RECYCLED-- YAY!!!!!");
        }

        if(mBoard.getTile(position).getStatus() == TileState.MARKED) {
            tileView.text.setTextColor(Color.WHITE);
        }
        else
            tileView.text.setTextColor(Color.RED);

        tileView.text.setText(mBoard.getTile(position).getStatus().toString());

        if(mBoard.getTile(position).getStatus() != TileState.NONE &&
                mBoard.getTile(position).getStatus() != TileState.MARKED && isPlayerBoard) {
            tileView.setBackgroundColor(0x96353535);
        } else {
            tileView.setBackgroundColor(0xFF353535);
        }

        return tileView;
    }

}
