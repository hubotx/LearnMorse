package pl.hubot.dev.learn_morse.model;

/**
 * Class that represents basics settings of keyer.
 */
public class MorseSettings {
	/**
	 * Constructor with default settings, used for debugging.
	 */
	public MorseSettings() {
		this.pauseBeforeKeying = 2;
		this.keyingSpeed = 20;
		this.ditLength = 1200 / keyingSpeed;
		this.dahLength = 3 * this.ditLength;
		this.lengthOfSpaceBetweenCharacters = 3 * this.ditLength;
		this.lengthOfSpaceBetweenWords = 7 * this.ditLength;
		this.lengthOfSpaceBetweenCharElements = this.ditLength;
		this.frequency = 800;
		this.volume = 0.1f;
		this.muted = false;
		this.pool = CharPool.englishSet;
	}

	/**
	 * Constructor that allows define pause before keying, keying speed and keyer frequency. Used for debugging.
	 * @param pauseBeforeKeying pause before keying
	 * @param keyingSpeed keying speed
	 * @param frequency keyer frequency
	 */
	public MorseSettings(int pauseBeforeKeying, int keyingSpeed, int frequency) {
		this.keyingSpeed = keyingSpeed;
		this.ditLength = 1200 / keyingSpeed;
		this.dahLength = 3 * this.ditLength;
		this.lengthOfSpaceBetweenCharacters = 3 * this.ditLength;
		this.lengthOfSpaceBetweenWords = 7 * this.ditLength;
		this.lengthOfSpaceBetweenCharElements = this.ditLength;
		this.pauseBeforeKeying = pauseBeforeKeying;
		this.frequency = frequency;
		this.volume = 0.1f;
		this.muted = false;
		this.pool = CharPool.englishSet;
	}

	/**
	 * Constructor that allows define pause before keying, keying speed, keyer frequency and volume. Used for debugging.
	 * @param pauseBeforeKeying pause before keying
	 * @param keyingSpeed keying speed
	 * @param frequency keyer frequency
	 * @param volume volume
	 */
	public MorseSettings(int pauseBeforeKeying, int keyingSpeed, int frequency, float volume) {
		this.keyingSpeed = keyingSpeed;
		this.ditLength = 1200 / keyingSpeed;
		this.dahLength = 3 * this.ditLength;
		this.lengthOfSpaceBetweenCharacters = 3 * this.ditLength;
		this.lengthOfSpaceBetweenWords = 7 * this.ditLength;
		this.lengthOfSpaceBetweenCharElements = this.ditLength;
		this.pauseBeforeKeying = pauseBeforeKeying;
		this.frequency = frequency;
		this.volume = volume;
		this.muted = false;
		this.pool = CharPool.englishSet;
	}

	/**
	 * Constructor that allows define all parameters except ditLength and dahLength. Used for debugging.
	 * @param pauseBeforeKeying pause before keying
	 * @param lengthOfSpaceBetweenCharacters length of space between two characters
	 * @param lengthOfSpaceBetweenWords length of space between two words
	 * @param lengthOfSpaceBetweenCharElements length of space between two char elements
	 * @param keyingSpeed keying speed
	 * @param frequency keyer frequency
	 * @param volume volume
	 */
	public MorseSettings(int pauseBeforeKeying, int lengthOfSpaceBetweenCharacters, int lengthOfSpaceBetweenWords, int lengthOfSpaceBetweenCharElements, int keyingSpeed, int frequency, float volume) {
		this.pauseBeforeKeying = pauseBeforeKeying;
		this.lengthOfSpaceBetweenCharacters = lengthOfSpaceBetweenCharacters;
		this.lengthOfSpaceBetweenWords = lengthOfSpaceBetweenWords;
		this.lengthOfSpaceBetweenCharElements = lengthOfSpaceBetweenCharElements;
		this.keyingSpeed = keyingSpeed;
		this.ditLength = 1200 / keyingSpeed;
		this.dahLength = 3 * this.ditLength;
		this.frequency = frequency;
		this.volume = volume;
		this.muted = false;
		this.pool = CharPool.englishSet;
	}

	/**
	 * Constructor that allows define all parameters.
	 * @param pauseBeforeKeying pause before keying
	 * @param lengthOfSpaceBetweenCharacters length of space between two characters
	 * @param lengthOfSpaceBetweenWords length of space between two words
	 * @param lengthOfSpaceBetweenCharElements length of space between two char elements
	 * @param keyingSpeed keying speed
	 * @param ditLength dit length
	 * @param dahLength dah length
	 * @param frequency keyer frequency
	 * @param volume volume
	 * @param muted is keyer muted
	 * @param pool character pool
	 */
	public MorseSettings(int pauseBeforeKeying, int lengthOfSpaceBetweenCharacters, int lengthOfSpaceBetweenWords, int lengthOfSpaceBetweenCharElements, int keyingSpeed, int ditLength, int dahLength, int frequency, float volume, boolean muted, char[] pool) {
		this.pauseBeforeKeying = pauseBeforeKeying;
		this.lengthOfSpaceBetweenCharacters = lengthOfSpaceBetweenCharacters;
		this.lengthOfSpaceBetweenWords = lengthOfSpaceBetweenWords;
		this.lengthOfSpaceBetweenCharElements = lengthOfSpaceBetweenCharElements;
		this.keyingSpeed = keyingSpeed;
		this.ditLength = ditLength * (1200 / keyingSpeed);
		this.dahLength = dahLength * this.ditLength;
		this.frequency = frequency;
		this.volume = volume;
		this.muted = muted;
		this.pool = pool;
	}

	/**
	 * Get pause before keying.
	 * @return pause before keying
	 */
	public int getPauseBeforeKeying() {
		return pauseBeforeKeying;
	}

	/**
	 * Set pause before keying.
	 * @param pauseBeforeKeying pause before keying
	 */
	public void setPauseBeforeKeying(int pauseBeforeKeying) {
		this.pauseBeforeKeying = pauseBeforeKeying;
	}

	/**
	 * Get length of space between two characters.
	 * @return length of space between two characters
	 */
	public int getLengthOfSpaceBetweenCharacters() {
		return lengthOfSpaceBetweenCharacters;
	}

	/**
	 * Set length of space between two characters.
	 * @param lengthOfSpaceBetweenCharacters length of space between two characters
	 */
	public void setLengthOfSpaceBetweenCharacters(int lengthOfSpaceBetweenCharacters) {
		this.lengthOfSpaceBetweenCharacters = lengthOfSpaceBetweenCharacters;
	}

	/**
	 * Get length of space between two words.
	 * @return length of space between two words
	 */
	public int getLengthOfSpaceBetweenWords() {
		return lengthOfSpaceBetweenWords;
	}

	/**
	 * Set length of space between two words.
	 * @param lengthOfSpaceBetweenWords length of space between two words
	 */
	public void setLengthOfSpaceBetweenWords(int lengthOfSpaceBetweenWords) {
		this.lengthOfSpaceBetweenWords = lengthOfSpaceBetweenWords;
	}

	/**
	 * Get length of space between two char elements.
	 * @return length of space between two char elements
	 */
	public int getLengthOfSpaceBetweenCharElements() {
		return lengthOfSpaceBetweenCharElements;
	}

	/**
	 * Set length of space between two char elements.
	 * @param lengthOfSpaceBetweenCharElements length of space between two char elements
	 */
	public void setLengthOfSpaceBetweenCharElements(int lengthOfSpaceBetweenCharElements) {
		this.lengthOfSpaceBetweenCharElements = lengthOfSpaceBetweenCharElements;
	}

	/**
	 * Get keying speed.
	 * @return keying speed
	 */
	public int getKeyingSpeed() {
		return keyingSpeed;
	}

	/**
	 * Set keying speed.
	 * @param keyingSpeed keying speed
	 */
	public void setKeyingSpeed(int keyingSpeed) {
		this.keyingSpeed = keyingSpeed;
	}

	/**
	 * Get keyer frequency.
	 * @return keyer frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Set keyer frequency.
	 * @param frequency keyer frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Get dit length.
	 * @return dit length
	 */
	public int getDitLength() {
		return ditLength;
	}

	/**
	 * Set dit length.
	 * @param ditLength dit length
	 */
	public void setDitLength(int ditLength) {
		this.ditLength = ditLength;
	}

	/**
	 * Get dah length.
	 * @return dah length
	 */
	public int getDahLength() {
		return dahLength;
	}

	/**
	 * Set dah length.
	 * @param dahLength dah length
	 */
	public void setDahLength(int dahLength) {
		this.dahLength = dahLength;
	}

	/**
	 * Get buzzer volume.
	 * @return buzzer volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * Set buzzer volume.
	 * @param volume buzzer volume
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}

	public char[] getPool() {
		return pool;
	}

	public void setPool(char[] pool) {
		this.pool = pool;
	}

	private int pauseBeforeKeying;
	private int lengthOfSpaceBetweenCharacters;
	private int lengthOfSpaceBetweenWords;
	private int lengthOfSpaceBetweenCharElements;
	private int keyingSpeed;
	private int ditLength;
	private int dahLength;
	private int frequency;
	private float volume;
	private boolean muted;
	private char[] pool;
	// private String soundcard;
}
