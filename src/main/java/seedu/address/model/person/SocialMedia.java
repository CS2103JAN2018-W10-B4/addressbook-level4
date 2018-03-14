package seedu.address.model.person;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SocialMedia {

    //currently only supports facebook and wechat
    private String facebook_url;
    private String wechat_username;

    /**
     * Constructs {@code SocialMedia accounts}.
     *
     * @param  urls or usernames used to connect with someone over social media
     */
    public SocialMedia(String facebook, String wechat) {
        if (facebook != null) {
            this.facebook_url = facebook;
        } else {
            facebook_url = null;
        }
        if (wechat != null) {
            this.wechat_username = wechat;
        } else {
            wechat_username = null;
        }
    }

    //can later add requirements in setter methods for validating requests

    String getFacebook() {
        return facebook_url;
    }

    boolean setFacebook(String url) {
        facebook_url = url;
        return true;
    }

    String getWeChat() {
        return wechat_username;
    }

    boolean setWeChat(String user) {
        wechat_username = user;
        return true;
    }
}
