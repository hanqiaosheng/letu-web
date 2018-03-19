package org.service.report;

import java.util.List;

import org.entity.dto.FixedReturn;

public interface FixedReturnServiceReport {
	public FixedReturn findFixedById(Long fixedId)throws Exception;

	public List<FixedReturn> findByChannels(List<Long> channelIds)throws Exception;
}