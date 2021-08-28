package top.heue.mq.helper;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by gongw on 2018/7/13.
 */

public class SimpleAdapter<T> extends RecyclerView.Adapter<SimpleAdapter.BaseViewHolder> {

    public List<T> itemData;
    private final int defaultLayout;
    private final int brId;

    public SimpleAdapter(List<T> itemData, int defaultLayout, int brId) {
        this.itemData = itemData;
        this.defaultLayout = defaultLayout;
        this.brId = brId;
    }

    public int getItemLayout(T itemData) {
        return defaultLayout;
    }

    public void onItemChanged(int position, T item) {
        notifyItemChanged(position);
    }

    public void onItemRemoved(int position) {
        notifyItemRemoved(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onItemDataChanged(List<T> newItemData) {
        this.itemData = newItemData;
        notifyDataSetChanged();
    }

    protected void onItemRangeChanged(List<T> newItemData, int positionStart, int itemCount) {
        this.itemData = newItemData;
        notifyItemRangeChanged(positionStart, itemCount);
    }

    protected void onItemRangeInserted(List<T> newItemData, int positionStart, int itemCount) {
        this.itemData = newItemData;
        notifyItemRangeInserted(positionStart, itemCount);
    }

    protected void onItemRangeRemoved(List<T> newItemData, int positionStart, int itemCount) {
        this.itemData = newItemData;
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemLayout(itemData.get(position));
    }

    @NotNull
    @Override
    public SimpleAdapter.BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.binding.setVariable(brId, itemData.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return itemData == null ? 0 : itemData.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}