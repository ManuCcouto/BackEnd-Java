package com.mca.domain.exception;

public class NoSuchElementException extends RuntimeException {

  private static final long serialVersionUID = 7824416863383128610L;

  public NoSuchElementException(final String msg) {
    super(msg);
  }
}
