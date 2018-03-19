package org.service.weixin.read;

import java.util.List;

import org.entity.dto.HotWord;

public interface HotWordWxServiceRead {

	public List<HotWord> findAllHotWord() throws Exception;

}
