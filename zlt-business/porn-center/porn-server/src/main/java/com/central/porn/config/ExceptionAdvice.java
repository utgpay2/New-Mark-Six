package com.central.porn.config;

import com.central.common.exception.DefaultExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}
