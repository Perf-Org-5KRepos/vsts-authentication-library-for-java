// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See License.txt in the project root.

package com.microsoft.alm.storage.windows;

import com.microsoft.alm.secret.TokenPair;
import com.microsoft.alm.storage.windows.internal.CredManagerBackedSecureStore;

public class CredManagerBackedTokenPairStore extends CredManagerBackedSecureStore<TokenPair> {

    public static final String TOKEN_PAIR_USERNAME = "Azure Active Directory Access and Refresh Token";
    @Override
    protected TokenPair create(final String username, final String secret) {
        return new TokenPair("", secret);
    }

    @Override
    protected String getUsername(final TokenPair tokenPair) {
        return TOKEN_PAIR_USERNAME;
    }

    @Override
    protected String getCredentialBlob(final TokenPair tokenPair) {
        // Only save refresh token on Windows
        // Cred Manager has a 4K size limit, need to make sure we stay under this limit
        return tokenPair.RefreshToken.Value;
    }
}
