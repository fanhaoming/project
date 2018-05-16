package com.project.core.utils;

public interface ListSelectAction<SourceT, DestinationT> {

	public DestinationT get(SourceT source);
}
