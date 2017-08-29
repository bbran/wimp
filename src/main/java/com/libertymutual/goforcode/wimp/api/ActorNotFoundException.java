package com.libertymutual.goforcode.wimp.api;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class ActorNotFoundException extends Exception {

}
