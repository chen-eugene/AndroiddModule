package com.eugene.loading.core;

import com.eugene.loading.callback.Callback;

/**
 * Description:TODO
 * Create Time:2017/9/4 8:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface Converter<T> {
   Class<?extends Callback> map(T t);
}
