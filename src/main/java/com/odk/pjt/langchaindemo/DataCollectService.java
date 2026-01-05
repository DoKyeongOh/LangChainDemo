package com.odk.pjt.langchaindemo;

import java.util.stream.Stream;

public interface DataCollectService<T> {
    Stream<T> collect();
}
