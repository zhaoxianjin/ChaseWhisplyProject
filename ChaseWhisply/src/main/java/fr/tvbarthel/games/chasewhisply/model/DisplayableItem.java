package fr.tvbarthel.games.chasewhisply.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class DisplayableItem implements Parcelable {
	// x coordinate
	protected int mX;
	// y coordinate
	protected int mY;
	// width
	protected int mWidth;
	// height
	protected int mHeight;
	// tells if the item is active/visible
	protected boolean mIsVisible;

	public DisplayableItem(int x, int y) {
		mX = x;
		mY = y;
	}

	protected DisplayableItem(Parcel in) {
		readFromParcel(in);
	}

	/**
	 * If the item is located in the window, updateVisibility sets mIsVisible on true
	 * else updateVisibility sets mIsVisible on false
	 *
	 * @param windowX
	 * @param windowY
	 * @param windowWidth
	 * @param windowHeight
	 */
	public void updateVisibility(final float windowX, final float windowY, final int windowWidth, final int windowHeight) {
		final float borderLeft = windowX - mWidth;
		final float borderTop = windowY - mHeight;
		final float borderRight = borderLeft + windowWidth + mWidth;
		final float borderBottom = borderTop + windowHeight + mHeight;
		if (mX > borderLeft && mX < borderRight && mY > borderBottom && mY < borderTop) {
			mIsVisible = true;
		} else {
			mIsVisible = false;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int i) {
		out.writeInt(mX);
		out.writeInt(mY);
		out.writeInt(mWidth);
		out.writeInt(mHeight);
		out.writeByte((byte) (mIsVisible ? 1 : 0));
	}

	/**
	 * read parameters from a given Parcel
	 *
	 * @param in input Parcel to read from
	 */
	public void readFromParcel(Parcel in) {
		this.mX = in.readInt();
		this.mY = in.readInt();
		this.mWidth = in.readInt();
		this.mHeight = in.readInt();
		this.mIsVisible = in.readByte() == 1;
	}

	public static final Parcelable.Creator<DisplayableItem> CREATOR = new Parcelable.Creator<DisplayableItem>() {
		public DisplayableItem createFromParcel(Parcel in) {
			return new DisplayableItem(in);
		}

		public DisplayableItem[] newArray(int size) {
			return new DisplayableItem[size];
		}
	};

	/**
	 * Getters and Setters
	 */
	public int getX() {
		return mX;
	}

	public void setX(int mX) {
		this.mX = mX;
	}

	public int getY() {
		return mY;
	}

	public void setY(int mY) {
		this.mY = mY;
	}

	public int getWidth() {
		return mWidth;
	}

	public void setWidth(int mWidth) {
		this.mWidth = mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int mHeight) {
		this.mHeight = mHeight;
	}

	public boolean isActive() {
		return mIsVisible;
	}

	public void setActive(boolean active) {
		mIsVisible = active;
	}

}
