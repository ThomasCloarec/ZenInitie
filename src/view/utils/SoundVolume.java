package view.utils;

public enum SoundVolume {
    NONE(0),
    LOW(1.0d / 3.0d),
    MEDIUM(2.0d / 3.0d),
    HIGH(1);
    private final double value;

    SoundVolume(double value) {
        this.value = value;
    }

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

    public double getValue() {
        return this.value;
    }
}
