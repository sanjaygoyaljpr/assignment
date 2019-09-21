package com.kristal.constant;

public interface ApplicationConstant {

	enum DURATION {

		FIVE_MINUTES(1500), TEM_MINUTES(3000), THIRTY_MINUTES(9000);

		int duration;

		DURATION(int duration) {
			this.duration = duration;
		}

		public int getDuration() {
			return duration;
		}
	}

}
