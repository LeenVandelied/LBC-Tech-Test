package com.adevinta.home.di

import com.adevinta.core.di.IoDispatcher
import com.adevinta.home.albumlist.AlbumListViewModel
import com.adevinta.home.albumlist.domain.GetAlbumsUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModules = module { single { GetAlbumsUseCase(get(named(IoDispatcher)), get()) } }

val viewModelModules = module { viewModelOf(::AlbumListViewModel) }

val homeModules = module { includes(useCaseModules, viewModelModules) }
