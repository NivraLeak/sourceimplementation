package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Skill;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.SkillRepository;
import com.acme.ideogo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Page<Skill> getAllSkillsByTagId(Long tagId, Pageable pageable) {
        return skillRepository.findByTagId(tagId,pageable);

    }

    @Override
    public Skill getSkillByIdAndTagId(Long tagId, Long skillId) {
        return skillRepository.findByIdAndTagId(skillId,tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill not found with Id " + skillId +
                                " and TagId " + tagId ));
    }

    @Override
    public Skill createSkill(Long tagId, Skill skill) {
        return tagRepository.findById(tagId).map(tag -> {
            skill.setTag(tag);
            return skillRepository.save(skill);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tag", "Id", tagId));
    }

    @Override
    public Skill updateSkill(Long tagId, Long skillId, Skill skillDetails) {
        if(!tagRepository.existsById(tagId))
            throw new ResourceNotFoundException("Tag", "Id", tagId);

        return skillRepository.findById(skillId).map(skill -> {
            skill.setDegreesRequirement(skillDetails.getDegreesRequirement());
            return skillRepository.save(skill);
        }).orElseThrow(() -> new ResourceNotFoundException("Skill", "Id", skillId));

    }

    @Override
    public ResponseEntity<?> deleteSkill(Long tagId, Long skillId)
    {
        return skillRepository.findByIdAndTagId(skillId, tagId).map(skill -> {
            skillRepository.delete(skill);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Skill not found with Id " + skillId + " and TagId " + tagId));


    }
}
