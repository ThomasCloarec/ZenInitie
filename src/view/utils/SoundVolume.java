package view.utils;

/**
 * The enum Sound volume.
 */
public enum SoundVolume {
    /**
     * None sound volume.
     */
    NONE(0),
    /**
     * Low sound volume.
     */
    LOW(1.0d / 3.0d),
    /**
     * Medium sound volume.
     */
    MEDIUM(2.0d / 3.0d),
    /**
     * High sound volume.
     */
    HIGH(1);
    /**
     * The Value.
     */
    private final double value;

    /**
     * Instantiates a new Sound volume.
     *
     * @param value the value
     */
    SoundVolume(double value) {
        this.value = value;
    }

    /**
     * Gets icon path.
     *
     * @return the icon path
     */
    public static String getIconPath() {
        String volumeImagePath;
        if (Sound.getVolume() == SoundVolume.LOW) {
            volumeImagePath = "icons/volume_low.png";
        } else if (Sound.getVolume() == SoundVolume.MEDIUM) {
            volumeImagePath = "icons/volume_medium.png";
        } else if (Sound.getVolume() == SoundVolume.HIGH) {
            volumeImagePath = "icons/volume_high.png";
        } else {
            volumeImagePath = "icons/volume_none.png";
        }

        return volumeImagePath;
    }

    /**
     * Next volume tick sound volume.
     *
     * @return the sound volume
     */
    public SoundVolume nextVolumeTick() {
        SoundVolume nextSoundVolume;
        if (this == SoundVolume.NONE) {
            nextSoundVolume = SoundVolume.LOW;
        } else if (this == SoundVolume.LOW) {
            nextSoundVolume = SoundVolume.MEDIUM;
        } else if (this == SoundVolume.MEDIUM) {
            nextSoundVolume = SoundVolume.HIGH;
        } else {
            nextSoundVolume = SoundVolume.NONE;
        }

        return nextSoundVolume;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return this.value;
    }
}
