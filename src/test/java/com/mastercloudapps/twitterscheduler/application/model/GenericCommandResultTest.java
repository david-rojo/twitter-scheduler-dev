package com.mastercloudapps.twitterscheduler.application.model;

import static com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.ResultErrorCode.MESSAGE_MAX_LENGTH_EXCEEDED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult;
import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.ResultErrorCode;

class GenericCommandResultTest {
	
	public GenericCommandResult create(MockData data) {
	    return (data.result) ? GenericCommandResult.success() : GenericCommandResult.error(data.resultErrorCode);
	  }

	  enum MockData {
	    SUCCESS(true, null),
	    ERROR_MESSAGE_MAX_LENGTH_EXCEEDED(false, MESSAGE_MAX_LENGTH_EXCEEDED);

	    private final boolean result;

	    private final ResultErrorCode resultErrorCode;

	    MockData(boolean result, ResultErrorCode resultErrorCode) {
	      this.result = result;
	      this.resultErrorCode = resultErrorCode;
	    }
	  }

	  @Nested
	  @DisplayName("Test plan creation success")
	  class TestPlanCreationSuccess {

	    private GenericCommandResult genericCommandResult;

	    @BeforeEach
	    void setUp() {
	      genericCommandResult = create(GenericCommandResultTest.MockData.SUCCESS);
	    }

	    @Test
	    @DisplayName("Test creation, expected not null")
	    void testNotNull() {
	      assertThat(genericCommandResult, is(notNullValue()));
	    }

	    @Test
	    @DisplayName("Test creation, expected result")
	    void testEqualsResult() {
	      assertThat(genericCommandResult.isSuccess(), is(GenericCommandResultTest.MockData.SUCCESS.result));
	    }

	    @Test
	    @DisplayName("Test creation, expected resultCode")
	    void testEqualsResultCode() {
	      assertThat(genericCommandResult.getResultErrorCode().isEmpty(), is(true));
	    }

	  }

	  @Nested
	  @DisplayName("Test plan creation error")
	  class TestPlanCreationError {

	    private GenericCommandResult genericCommandResult;

	    @BeforeEach
	    void setUp() {
	      genericCommandResult = create(GenericCommandResultTest.MockData.ERROR_MESSAGE_MAX_LENGTH_EXCEEDED);
	    }

	    @Test
	    @DisplayName("Test creation, expected not null")
	    void testNotNull() {
	      assertThat(genericCommandResult, is(notNullValue()));
	    }

	    @Test
	    @DisplayName("Test creation, expected result")
	    void testEqualsCode() {
	      assertThat(genericCommandResult.isSuccess(), is(GenericCommandResultTest.MockData.ERROR_MESSAGE_MAX_LENGTH_EXCEEDED.result));
	    }

	    @Test
	    @DisplayName("Test creation, expected resultCode")
	    void testEqualsResultCode() {
	      assertThat(genericCommandResult.getResultErrorCode().isPresent(), is(true));

	      genericCommandResult.getResultErrorCode()
	          .ifPresent(r -> assertThat(r, is(GenericCommandResultTest.MockData.ERROR_MESSAGE_MAX_LENGTH_EXCEEDED.resultErrorCode)));
	    }
	  }

}
