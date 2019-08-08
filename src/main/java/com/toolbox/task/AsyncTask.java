package com.toolbox.task;

import org.springframework.scheduling.annotation.Async;

public class AsyncTask {

	@Async
	public void doTaskOne() throws Exception {
		// 同上内容，省略
	}

	@Async
	public void doTaskTwo() throws Exception {
		// 同上内容，省略
	}

	@Async
	public void doTaskThree() throws Exception {
		// 同上内容，省略
	}
}
