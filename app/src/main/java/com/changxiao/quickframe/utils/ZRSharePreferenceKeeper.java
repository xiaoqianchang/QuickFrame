package com.changxiao.quickframe.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.prefs.Preferences;

/**
 * Share preference utils, used for reading and saving share preference
 * conveniently.
 *
 * Created by Chang.Xiao on 2016/7/4.
 *
 * @version 1.0
 */
public class ZRSharePreferenceKeeper {

	private static final String PREFERENCES_NAME = "zrpreference";
	private static final String PREFERENCES_PERSONAL_NAME = "zrpreference_per";

	private static ZRSharePreferenceKeeper instance = null;
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor mEditor;
	boolean exist = false;

	/**
	 * 根据此方法获取实例，配置文件名将是程序的包名
	 *
	 * @param context
	 *            Context
	 * @return Preferences对象
	 */
	public synchronized static ZRSharePreferenceKeeper getPrefer(Context context) {
		if (null == instance) {
			instance = new ZRSharePreferenceKeeper(context, null);
		}
		return instance;
	}

	/**
	 * @param context
	 *            Context
	 * @param name
	 *            配置文件名称
	 * @return Preferences
	 */
	public synchronized static ZRSharePreferenceKeeper getPrefer(Context context, String name) {
		if (null == instance) {
			instance = new ZRSharePreferenceKeeper(context, name);
		}
		return instance;
	}

	/**
	 * @param context
	 *            Context
	 * @param name
	 *            配置文件名
	 */
	private ZRSharePreferenceKeeper(Context context, String name) {
		if (null == name || name.trim().length() == 0) {
			name = context.getPackageName();
		}
		mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	/**
	 * 写入字符串信息
	 *
	 * @param key
	 *            键
	 * @param value
	 *            String
	 */
	public void putString(String key, String value) {
		mEditor.putString(key, value);
		ZRSharePreferenceDelegate.commit(mEditor);
	}

	/**
	 * 获取字符串信息
	 *
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return String
	 */
	public String getString(String key, String defValue) {
		return mSharedPreferences.getString(key, defValue);
	}

	/**
	 * 写入long型数据
	 *
	 * @param key
	 *            键
	 * @param value
	 *            long
	 */
	public void putLong(String key, long value) {
		mEditor.putLong(key, value);
		ZRSharePreferenceDelegate.commit(mEditor);
	}

	/**
	 * 获取long型数据
	 *
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return long
	 */
	public long getLong(String key, long defValue) {
		return mSharedPreferences.getLong(key, defValue);
	}

	/**
	 * 写入int数据
	 *
	 * @param key
	 *            键
	 * @param value
	 *            int
	 */
	public void putInt(String key, int value) {
		mEditor.putInt(key, value);
		ZRSharePreferenceDelegate.commit(mEditor);
	}

	/**
	 * 获取int数据
	 *
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return int
	 */
	public int getInt(String key, int defValue) {
		return mSharedPreferences.getInt(key, defValue);
	}

	/**
	 * 写入boolean值
	 *
	 * @param key
	 *            键
	 * @param flag
	 *            boolean
	 */
	public void putBoolean(String key, boolean flag) {
		mEditor.putBoolean(key, flag);
		ZRSharePreferenceDelegate.commit(mEditor);
	}

	/**
	 * 获取boolean值
	 *
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return boolean
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSharedPreferences.getBoolean(key, defValue);
	}

	/**
	 * 写入float值
	 *
	 * @param key
	 * @param value
	 */
	public void putFloat(String key, float value) {
		mEditor.putFloat(key, value);
		ZRSharePreferenceDelegate.commit(mEditor);
	}

	/**
	 * 获取float值
	 *
	 * @param key
	 *            键
	 * @param defValue
	 *            默认值
	 * @return float
	 */
	public float getFloat(String key, float defValue) {
		return mSharedPreferences.getFloat(key, defValue);
	}

	/**
	 * 删除某项
	 *
	 * @param key
	 *            键
	 */
	public void remove(String key) {
		mEditor.remove(key);
		ZRSharePreferenceDelegate.commit(mEditor);
	}

	/**
	 * 是否已经存在某项
	 *
	 * @param key
	 *            键
	 * @return 存在返回true，否则返回false
	 */
	public boolean exist(String key) {
		return mSharedPreferences.contains(key);
	}

}
