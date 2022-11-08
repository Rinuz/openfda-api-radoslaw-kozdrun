package pl.kozdrun.openfdaapi.rest.api.openfda.util;

import org.apache.commons.lang3.tuple.Pair;

public class OpenFdaUrlBuilder {

    private static final String PAGE_PARAM = "skip";
    private static final String LIMIT_PARAM = "limit";

    private String baseUrl;
    private int page;
    private int limit;
    private Pair<String, Object>[] searchParams;

    public OpenFdaUrlBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public OpenFdaUrlBuilder withPage(int page) {
        this.page = page;
        return this;
    }

    public OpenFdaUrlBuilder withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public OpenFdaUrlBuilder withBaseUrl(Pair<String, Object>[] searchParams) {
        this.searchParams = searchParams;
        return this;
    }

    public String buildUrl() {
        StringBuilder uriComponentsBuilder = new StringBuilder(baseUrl).append("?");

        if (searchParams != null && searchParams.length > 0) {
            String searchParamsUrl = buildSearchParamsUrl(searchParams);
            uriComponentsBuilder = uriComponentsBuilder
                    .append("search=")
                    .append(searchParamsUrl)
                    .append("&");
        }

        uriComponentsBuilder = uriComponentsBuilder
                .append(PAGE_PARAM)
                .append("=")
                .append(page)
                .append("&")
                .append(LIMIT_PARAM)
                .append("=")
                .append(limit);

        return uriComponentsBuilder.toString();
    }

    private String buildSearchParamsUrl(Pair<String, Object>... searchParams) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, Object> paramPair : searchParams) {
            if (paramPair.getValue() != null) {
                sb.append(paramPair.getKey());
                sb.append(":");
                sb.append("\"");
                sb.append(paramPair.getValue());
                sb.append("\"");
                sb.append(",");
            }
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }
}
