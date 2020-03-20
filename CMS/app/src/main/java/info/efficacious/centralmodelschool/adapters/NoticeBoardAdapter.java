package info.efficacious.centralmodelschool.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import info.efficacious.centralmodelschool.R;
import info.efficacious.centralmodelschool.entity.DashboardDetail;


public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.ViewHolder> {
    private ArrayList<DashboardDetail> data;
    Context context;
    public NoticeBoardAdapter(ArrayList<DashboardDetail> dataList, Context context) {
        data = dataList;
        this.context=context;
    }

    @Override
    public NoticeBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.noticeboard_row,null);
        ViewHolder viewHolder=new ViewHolder(itemLayoutView);
        return viewHolder;
    }



    public void onBindViewHolder(final ViewHolder holder, int position) {
        try
        {
            holder.Lastdate.setTextColor(Color.RED);
            holder.Issuedate.setText(data.get(position).getIssueDate().toString());
            holder.Lastdate.setText(data.get(position).getEndDate().toString());
            holder.Notice.setText("Notice:"+data.get(position).getNotice().toString());
            holder.Subject.setText("Subject:"+data.get(position).getSubject().toString());
            Glide.with(context).load(data.get(position).getImageUrl()).into(holder.noticeIV);
            holder.noticeIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog imageDialog = new Dialog(context);
                    imageDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    imageDialog.setContentView(LayoutInflater.from(context).inflate(R.layout.image_layout
                            , null));
                    ImageView img_notice = imageDialog.findViewById(R.id.img_notice);
                    Glide.with(context).load(data.get(position).getImageUrl()).into(img_notice);
                    Button notice_dialog_cancel = imageDialog.findViewById(R.id.notice_dialog_cancel);
                    notice_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageDialog.cancel();
                        }
                    });
                    imageDialog.show();
                }
            });
        }catch (Exception ex)
        {
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Issuedate,Lastdate,Notice,Subject;
        ImageView noticeIV;
        public ViewHolder(View itemView) {
            super(itemView);
            Issuedate=(TextView)itemView.findViewById(R.id.fromdate_noticeboard);
            Lastdate=(TextView)itemView.findViewById(R.id.todate_noticeboard);
            Notice=(TextView)itemView.findViewById(R.id.message_noticeboard);
            Subject=(TextView)itemView.findViewById(R.id.title_noticeboard);
            noticeIV=itemView.findViewById(R.id.noticeIV);
        }
    }
}
