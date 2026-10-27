package com.example.myservice;

import static java.util.Collections.singletonList;
import static org.schabi.newpipe.extractor.StreamingService.ServiceInfo.MediaCapability.VIDEO;

import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.ChannelTabExtractor;
import org.schabi.newpipe.extractor.comments.CommentsExtractor;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.kiosk.KioskList;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.plugin.PluginPreferences;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.subscription.SubscriptionExtractor;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;

/**
 * Skeleton service. The serviceId is assigned by the host and must be passed on unchanged.
 * Every capability you do not support can return null or throw; implement the ones you do
 * (start with {@link #getStreamLHFactory()} and {@link #getStreamExtractor(LinkHandler)}).
 */
public class MyService extends StreamingService {

    private final PluginPreferences preferences;

    public MyService(final int id, final PluginPreferences preferences) {
        super(id, "MyService", singletonList(VIDEO));
        this.preferences = preferences;
    }

    /** Reads the "base_url" setting declared in plugin.json. */
    @Override
    public String getBaseUrl() {
        return preferences.getString("base_url", "https://example.com");
    }

    @Override
    public LinkHandlerFactory getStreamLHFactory() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public ListLinkHandlerFactory getChannelLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getChannelTabLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getPlaylistLHFactory() {
        return null;
    }

    @Override
    public SearchQueryHandlerFactory getSearchQHFactory() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public ListLinkHandlerFactory getCommentsLHFactory() {
        return null;
    }

    @Override
    public SearchExtractor getSearchExtractor(final SearchQueryHandler queryHandler) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public SuggestionExtractor getSuggestionExtractor() {
        return null;
    }

    @Override
    public SubscriptionExtractor getSubscriptionExtractor() {
        return null;
    }

    @Override
    public KioskList getKioskList() throws ExtractionException {
        return new KioskList(this);
    }

    @Override
    public ChannelExtractor getChannelExtractor(final ListLinkHandler linkHandler)
            throws ExtractionException {
        throw new ExtractionException("Channels are not supported");
    }

    @Override
    public ChannelTabExtractor getChannelTabExtractor(final ListLinkHandler linkHandler)
            throws ExtractionException {
        throw new ExtractionException("Channels are not supported");
    }

    @Override
    public PlaylistExtractor getPlaylistExtractor(final ListLinkHandler linkHandler)
            throws ExtractionException {
        throw new ExtractionException("Playlists are not supported");
    }

    @Override
    public StreamExtractor getStreamExtractor(final LinkHandler linkHandler)
            throws ExtractionException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public CommentsExtractor getCommentsExtractor(final ListLinkHandler linkHandler)
            throws ExtractionException {
        throw new ExtractionException("Comments are not supported");
    }
}
