package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictCustomDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsDao;
import org.arpico.groupit.receipt.dao.InPropLoadingCustomDao;
import org.arpico.groupit.receipt.dao.InPropLoadingDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqCustomDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsCustomDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsDao;
import org.arpico.groupit.receipt.dao.InPropShedulesCustomDao;
import org.arpico.groupit.receipt.dao.InPropShedulesDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsCustomDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.ProposalServce;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProposalServiceImpl implements ProposalServce {

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private CommonMethodsUtility commonethodUtility;

	@Autowired
	private InTransactionsDao inTransactionDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private InPropAddBenefictDao addBenefictDao;

	@Autowired
	private InPropAddBenefictCustomDao addBenefictCustomDao;

	@Autowired
	private InPropFamDetailsDao famDetailsDao;

	@Autowired
	private InPropFamDetailsCustomDao famDetailsCustomDao;

	@Autowired
	private InPropLoadingDao propLoadingDao;

	@Autowired
	private InPropLoadingCustomDao propLoadingCustomDao;

	@Autowired
	private InPropMedicalReqDao propMedicalReqDao;

	@Autowired
	private InPropMedicalReqCustomDao propMedicalReqCustomDao;

	@Autowired
	private InPropNomDetailsDao propNomDetailsDao;

	@Autowired
	private InPropNomDetailsCustomDao propNomDetailsCustomDao;

	@Autowired
	private InPropPrePolsDao propPrePolsDao;

	@Autowired
	private InPropPrePolsCustomDao propPrePolsCustomDao;

	@Autowired
	private InPropShedulesDao propScheduleDao;

	@Autowired
	private InPropShedulesCustomDao propScheduleCustomDao;

	@Autowired
	private InPropSurrenderValsDao surrenderValDao;

	@Autowired
	private InPropSurrenderValsCustomDao surrenderValCustomDao;

	@Override
	public List<ProposalNoSeqNoDto> getProposalNoSeqNoDtoList(String val) throws Exception {
		List<ProposalNoSeqNoDto> proposalNoSeqNoDtos = new ArrayList<ProposalNoSeqNoDto>();

		List<ProposalNoSeqNoModel> list = inProposalCustomDao.getProposalNoSeqNoModelList(val);

		for (ProposalNoSeqNoModel proposalNoSeqNoModel : list) {
			proposalNoSeqNoDtos.add(getProposalNoSeqNoDto(proposalNoSeqNoModel));

		}

		return proposalNoSeqNoDtos;
	}

	private ProposalNoSeqNoDto getProposalNoSeqNoDto(ProposalNoSeqNoModel proposalNoSeqNoModel) {
		ProposalNoSeqNoDto dto = new ProposalNoSeqNoDto();
		dto.setProposalNo(proposalNoSeqNoModel.getProposalNo());
		dto.setSeqNo(proposalNoSeqNoModel.getSeqNo());
		return dto;
	}

	@Override
	public ProposalBasicDetailsDto getBasicDetails(Integer pprNum, Integer seqNum) throws Exception {
		InProposalBasicsModel basicsModel = inProposalCustomDao.geInProposalBasics(pprNum, seqNum);

		ProposalBasicDetailsDto basicDetailsDto = getBasicDetailsDto(basicsModel);

		return basicDetailsDto;
	}

	private ProposalBasicDetailsDto getBasicDetailsDto(InProposalBasicsModel basicsModel) {

		ProposalBasicDetailsDto basicDetailsDto = new ProposalBasicDetailsDto();

		basicDetailsDto.setAgentCode(basicsModel.getAgentCode());
		basicDetailsDto.setCustName(basicsModel.getCustName());
		basicDetailsDto.setCustTitle(basicDetailsDto.getCustTitle());
		basicDetailsDto.setProduct(basicsModel.getProduct());
		basicDetailsDto.setProposalNo(basicsModel.getProposalNo());
		basicDetailsDto.setSeqNo(basicsModel.getSeqNo());
		return basicDetailsDto;
	}

	@Override
	public ResponseEntity<Object> saveProposal(SaveReceiptDto saveReceiptDto) throws Exception {

		InProposalsModel inProposalsModel = inProposalCustomDao.getProposal(saveReceiptDto.getPropId(),
				saveReceiptDto.getPropSeq());

		if (inProposalsModel != null) {

			Integer pprNo = Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum());
			Integer seqNo = inProposalsModel.getInProposalsModelPK().getPrpseq();

			if (inProposalsModel.getPprsta().equalsIgnoreCase("L3")) {
				List<ProposalL3Dto> proposalL3Dtos = inProposalCustomDao.checkL3(saveReceiptDto.getPropId());
				if (!proposalL3Dtos.isEmpty()) {

					String[] numberGen = numberGenerator.generateNewId("", "", "POLCSQ", "");
					if (numberGen[0].equals("Success")) {

						InProposalsModel proposalsModelNew = getProposalPolicyStage(inProposalsModel, numberGen[1],
								saveReceiptDto);

						Integer updatedSeqNo = proposalsModelNew.getInProposalsModelPK().getPrpseq();

						List<InPropAddBenefitModel> addBenefitModels = addBenefictCustomDao.getBenefByPprSeq(pprNo,
								seqNo);
						if (addBenefitModels != null && !addBenefitModels.isEmpty()) {
							addBenefitModels = incrementSeqAddBenef(addBenefitModels, updatedSeqNo);
							addBenefictDao.save(addBenefitModels);
						}

						List<InPropFamDetailsModel> famDetailsModels = famDetailsCustomDao
								.getFamilyByPprNoAndSeqNo(pprNo, seqNo);
						if (famDetailsModels != null && !famDetailsModels.isEmpty()) {
							famDetailsModels = incrementSeqFamDetails(famDetailsModels, updatedSeqNo);
							famDetailsDao.save(famDetailsModels);
						}

						List<InPropLoadingModel> inPropLoadingModels = propLoadingCustomDao
								.getPropLoadingBuPprNumAndSeq(pprNo, seqNo);
						if (inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {
							inPropLoadingModels = getInPropLoadings(inPropLoadingModels, updatedSeqNo);
							propLoadingDao.save(inPropLoadingModels);
						}

						List<InPropMedicalReqModel> inPropMedicalReqModels = propMedicalReqCustomDao
								.getMedicalReqByPprNoAndSeq(pprNo, seqNo);
						if (inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {
							inPropMedicalReqModels = incrementPropMedical(inPropMedicalReqModels, updatedSeqNo);
							propMedicalReqDao.save(inPropMedicalReqModels);
						}

						List<InPropNomDetailsModel> propNomDetailsModels = propNomDetailsCustomDao
								.getNomByPprNoAndPprSeq(pprNo, seqNo);
						if (propNomDetailsModels != null && !propNomDetailsModels.isEmpty()) {
							propNomDetailsModels = incrementPropNomDetailsSeq(propNomDetailsModels, updatedSeqNo);
							propNomDetailsDao.save(propNomDetailsModels);
						}

						List<InPropPrePolsModel> inPropPrePolsModels = propPrePolsCustomDao
								.getPrePolByPprNoAndPprSeq(pprNo, seqNo);
						if (inPropPrePolsModels != null && !inPropPrePolsModels.isEmpty()) {
							inPropPrePolsModels = incrementPropPolSeq(inPropPrePolsModels, updatedSeqNo);
							propPrePolsDao.save(inPropPrePolsModels);

						}

						List<InPropSchedulesModel> propSchedulesModels = propScheduleCustomDao
								.getScheduleBuPprNoAndSeqNo(pprNo, seqNo);
						if (propSchedulesModels != null && !propSchedulesModels.isEmpty()) {
							propSchedulesModels = incremenntScheduleSeq(propSchedulesModels, updatedSeqNo);
							propScheduleDao.save(propSchedulesModels);
						}

						List<InPropSurrenderValsModel> propSurrenderValsModels = surrenderValCustomDao
								.getSurrenderValByInpprNoAndSeq(pprNo, seqNo);
						if (propSurrenderValsModels != null && !propSurrenderValsModels.isEmpty()) {
							propSurrenderValsModels = incrementSurrenderVals(propSurrenderValsModels, updatedSeqNo,proposalsModelNew.getPolnum());
							surrenderValDao.save(propSurrenderValsModels);
						}

					}

				}

			}

			InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
					saveReceiptDto);
			InBillingTransactionsModel inBillingTransactionsModel = commonethodUtility
					.getInBillingTransactionModel(inProposalsModel, saveReceiptDto, inTransactionsModel);
			inTransactionDao.save(inTransactionsModel);
			inBillingTransactionDao.save(inBillingTransactionsModel);

		}

		return null;
	}

	private List<InPropSurrenderValsModel> incrementSurrenderVals(
			List<InPropSurrenderValsModel> propSurrenderValsModels, Integer updatedSeqNo, String polNo) {
		propSurrenderValsModels.forEach(e -> {
			e.getInPropSurrenderValsPK().setPrpseq(updatedSeqNo);
			e.setPolnum(polNo);
		});
		return propSurrenderValsModels;
	}

	private List<InPropSchedulesModel> incremenntScheduleSeq(List<InPropSchedulesModel> propSchedulesModels,
			Integer updatedSeqNo) {
		propSchedulesModels.forEach(e -> {
			e.getInPropSchedulesPK().setPrpseq(updatedSeqNo);
		});
		return propSchedulesModels;
	}

	private List<InPropPrePolsModel> incrementPropPolSeq(List<InPropPrePolsModel> inPropPrePolsModels,
			Integer updatedSeqNo) {
		inPropPrePolsModels.forEach(e -> {
			e.getInPropPrePolsModelPK().setPrpseq(updatedSeqNo);
		});
		return inPropPrePolsModels;
	}

	private List<InPropNomDetailsModel> incrementPropNomDetailsSeq(List<InPropNomDetailsModel> propNomDetailsModels,
			Integer updatedSeqNo) {
		propNomDetailsModels.forEach(e -> {
			e.getInPropNomDetailsModelPK().setPrpseq(updatedSeqNo);
		});
		return propNomDetailsModels;
	}

	private List<InPropMedicalReqModel> incrementPropMedical(List<InPropMedicalReqModel> inPropMedicalReqModels,
			Integer seqNo) {
		inPropMedicalReqModels.forEach(e -> {
			e.getInPropMedicalReqModelPK().setPrpseq(seqNo);
		});
		return inPropMedicalReqModels;
	}

	private List<InPropLoadingModel> getInPropLoadings(List<InPropLoadingModel> inPropLoadingModels, Integer seqNo) {
		inPropLoadingModels.forEach(e -> {
			e.getInPropLoadingPK().setPrpseq(seqNo);
		});
		return inPropLoadingModels;
	}

	private List<InPropFamDetailsModel> incrementSeqFamDetails(List<InPropFamDetailsModel> famDetailsModels,
			Integer seqNo) {
		famDetailsModels.forEach(e -> {
			e.getInPropFamDetailsPK().setPrpseq(seqNo);
		});
		return famDetailsModels;
	}

	private List<InPropAddBenefitModel> incrementSeqAddBenef(List<InPropAddBenefitModel> addBenefitModels,
			Integer seqNo) {
		addBenefitModels.forEach(e -> {
			e.getInPropAddBenefitPK().setPrpseq(seqNo);
		});
		return addBenefitModels;
	}

	private InProposalsModel getProposalPolicyStage(InProposalsModel inProposalsModel, String polNo,
			SaveReceiptDto saveReceiptDto) {
		InProposalsModel proposalsModel = inProposalsModel;

		proposalsModel.getInProposalsModelPK().setPrpseq(proposalsModel.getInProposalsModelPK().getPrpseq() + 1);
		proposalsModel.setPolnum(polNo);
		proposalsModel.setPoldat(AppConstant.DATE);
		proposalsModel.setCreaby(AppConstant.SYSTEM_CREATE);
		proposalsModel.setCreadt(AppConstant.DATE);
		proposalsModel.setPprsta(AppConstant.POLICY_STATUS_PLISU);

		return proposalsModel;
	}

}
