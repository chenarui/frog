package com.frog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.ProposalMapper;
import com.frog.model.Proposal;
import com.frog.service.ProposalService;

public class ProposalServiceImpl implements ProposalService{
	@Autowired
	ProposalMapper mapper;
	@Override
	public void addProposal(Proposal proposal) {
		// TODO Auto-generated method stub
		mapper.insertProposal(proposal);
	}

}
