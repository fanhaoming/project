package com.project.core.dictionary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.core.dictionary.DictAnnotation;
import com.project.core.dictionary.DictionaryItem;
import com.project.core.dictionary.DictionaryLoader;
import com.project.core.dictionary.DictionaryType;
import com.project.core.utils.ListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DictJsonSerializer extends JsonSerializer<Object> {

	private DictAnnotation dictAnnotation;

	public void setDictAnnoation(DictAnnotation dictAnnotation) {
		this.dictAnnotation = dictAnnotation;
	}

	private void getParentDictType(List<DictionaryItem> treeDicts, DictionaryItem rootDict, List<DictionaryItem> dicts, int id){
		DictionaryItem dict = ListUtil.find(dicts, d -> {
			return d.getId() == id;
		});
		if(dict==null)
			return;
		treeDicts.add(dict);
		if(dict.getParentId()==null || dict.getParentId()==0){
			Collections.reverse(treeDicts); 
			return;			
		}
		getParentDictType(treeDicts,rootDict, dicts, dict.getParentId());		
	}
 
	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		DictionaryItem dictionaryItem = DictionaryLoader.getInstance().getDictionaryByValue(dictAnnotation.code(), Integer.valueOf(value.toString()));
		DictionaryType dictionaryType = DictionaryLoader.getInstance().getDictionaryTypeByCode(dictAnnotation.code());
		if (dictionaryItem != null && dictionaryType!=null) {
			
			gen.writeStartObject();
			gen.writeNumberField("id", dictionaryItem.getId());
			gen.writeStringField("name", dictionaryItem.getName());
			gen.writeStringField("code", dictionaryItem.getCode());
 
			if(dictionaryType.getIsTree()){

				List<DictionaryItem> treeDicts = new ArrayList<DictionaryItem>();
				getParentDictType(treeDicts, dictionaryItem, dictionaryType.getDicts(), Integer.valueOf(value.toString()));
				
				gen.writeFieldName("treeDicts");
				gen.writeStartArray();
				for (DictionaryItem dict : treeDicts) {
					gen.writeStartObject();
					gen.writeNumberField("id", dict.getId());
					gen.writeStringField("name", dict.getName());
					gen.writeStringField("code", dict.getCode());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
			
			gen.writeEndObject();
		} else {
			gen.writeStartObject();
			gen.writeEndObject();
		}
	}

}
