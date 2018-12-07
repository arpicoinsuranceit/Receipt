package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.client.InfosysWSClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.CodeTransferDao;
import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
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
import org.arpico.groupit.receipt.dao.InProposalDao;
import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.CodeTransferDto;
import org.arpico.groupit.receipt.dto.CodeTransferGridDto;
import org.arpico.groupit.receipt.dto.CodeTransferHelperDto;
import org.arpico.groupit.receipt.dto.EmailDto;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveCodeTransferDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.CodeTransferModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeTransferServiceImpl implements CodeTransferService {

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private InPropMedicalReqCustomDao inPropMedicalReqCustomDao;

	@Autowired
	private InPropMedicalReqDao inPropMedicalReqDao;

	@Autowired
	private CodeTransferDao codeTransferDao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private InPropLoadingCustomDao inPropLoadingCustomDao;

	@Autowired
	private InPropFamDetailsDao famDetailsDao;

	@Autowired
	private InPropFamDetailsCustomDao famDetailsCustomDao;

	@Autowired
	private InPropLoadingDao propLoadingDao;

	@Autowired
	private InPropMedicalReqDao propMedicalReqDao;

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

	@Autowired
	private InProposalDao inProposalDao;

	@Autowired
	private InPropAddBenefictCustomDao inPropAddBenefictCustomDao;

	@Autowired
	private InPropAddBenefictDao addBenefictDao;

	@Autowired
	private InTransactionCustomDao inTransactionCustomDao;

	@Autowired
	private InTransactionsDao inTransactionsDao;

	@Autowired
	private InAgentMastDao inAgentMastDao;

	@Autowired
	private InBillingTransactionsCustomDao inBillingTransactionsCustomDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionsDao;

	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;

	@Autowired
	private UserDao userDao;


	@Override
	public ResponseEntity<Object> getProposalDetails(String pprNum, String token) throws Exception {
		ResponseDto dto = null;
		String userCode = new JwtDecoder().generate(token);
		try {
			InProposalsModel inProposalsModel = inProposalCustomDao.getProposalFromPprnum(Integer.valueOf(pprNum));
			if (inProposalsModel != null) {
				if (inProposalsModel.getCreaby().equals(userCode)) {

					if (inProposalsModel.getPprsta().equals("L3")) {
						try {

							CodeTransferHelperDto codeTransferHelperDto = new CodeTransferHelperDto();
							codeTransferHelperDto.setAgentCode(inProposalsModel.getAdvcod());
							codeTransferHelperDto.setPprNum(pprNum);
							codeTransferHelperDto.setBranch(inProposalsModel.getInProposalsModelPK().getLoccod());
							AgentModel agentModel = agentDao.findPropAgent(inProposalsModel.getAdvcod());
							if (agentModel != null) {
								codeTransferHelperDto.setAgentName(agentModel.getAgentName());
								codeTransferHelperDto.setDesignation(agentModel.getDesignation());
							}

							return new ResponseEntity<>(codeTransferHelperDto, HttpStatus.OK);

						} catch (Exception ex) {
							dto = new ResponseDto();
							dto.setCode("204");
							dto.setStatus("Error");
							dto.setMessage("Unable to transfer code in this Proposal.");
							return new ResponseEntity<>(dto, HttpStatus.OK);
						}

					} else {
						dto = new ResponseDto();
						dto.setCode("204");
						dto.setStatus("Error");
						dto.setMessage("Unable to transfer code in this Proposal.");
						return new ResponseEntity<>(dto, HttpStatus.OK);

					}
				} else {
					dto = new ResponseDto();
					dto.setCode("204");
					dto.setStatus("Error");
					dto.setMessage("Unable to transfer code in this Proposal.");
					return new ResponseEntity<>(dto, HttpStatus.OK);
				}
			} else {
				dto = new ResponseDto();
				dto.setCode("204");
				dto.setStatus("Error");
				dto.setMessage("Proposal Number Not Found.");
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}

		} catch (Exception e) {
			dto = new ResponseDto();
			dto.setCode("204");
			dto.setStatus("Error");
			dto.setMessage("Proposal Not Found.");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<Object> getPolicyDetails(String polNum, String token) throws Exception {
		ResponseDto dto = null;
		String userCode = new JwtDecoder().generate(token);
		try {
			InProposalsModel inProposalsModel = inProposalCustomDao.getProposalFromPolnum(Integer.valueOf(polNum));

			if (inProposalsModel != null) {
				if (inProposalsModel.getCreaby().equals(userCode)) {
					CodeTransferHelperDto codeTransferHelperDto = new CodeTransferHelperDto();
					codeTransferHelperDto.setAgentCode(inProposalsModel.getAdvcod());
					codeTransferHelperDto.setPprNum(polNum);
					codeTransferHelperDto.setBranch(inProposalsModel.getInProposalsModelPK().getLoccod());
					AgentModel agentModel = agentDao.findPropAgent(inProposalsModel.getAdvcod());
					if (agentModel != null) {
						codeTransferHelperDto.setAgentName(agentModel.getAgentName());
						codeTransferHelperDto.setDesignation(agentModel.getDesignation());
					}

					return new ResponseEntity<>(codeTransferHelperDto, HttpStatus.OK);
				} else {
					dto = new ResponseDto();
					dto.setCode("204");
					dto.setStatus("Error");
					dto.setMessage("Unable to transfer code in this Policy.");
					return new ResponseEntity<>(dto, HttpStatus.OK);
				}
			} else {
				dto = new ResponseDto();
				dto.setCode("204");
				dto.setStatus("Error");
				dto.setMessage("Policy Number Not Found.");
				return new ResponseEntity<>(dto, HttpStatus.OK);

			}

		} catch (Exception e) {
			dto = new ResponseDto();
			dto.setCode("204");
			dto.setStatus("Error");
			dto.setMessage("Policy Number Not Found");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
	}

	@Override
	public List<CodeTransferDto> getPendingCodeTransferPrp(String token) throws Exception {
		String usercode = new JwtDecoder().generate(token);
		List<CodeTransferDto> codeTransferDtos = new ArrayList<>();
		if (usercode != null) {
			List<CodeTransferModel> codeTransferModels = codeTransferDao.findByStatusAndCreateBy("PENDING", usercode);
			System.out.println(codeTransferModels.size() + "size .. ");
			if (!codeTransferModels.isEmpty()) {
				codeTransferModels.forEach(code -> {
					if (code.getPolNum() == null || code.getPolNum() == "" || code.getPolNum().isEmpty()) {
						CodeTransferDto codeTransferDto = new CodeTransferDto();
						codeTransferDto.setApprovedBy(code.getApprovedBy());
						codeTransferDto.setApprovedDate(code.getApprovedDate());
						codeTransferDto.setApproverRemark(code.getApproverRemark());
						codeTransferDto.setCodeTransferId(code.getCodeTransferId());
						codeTransferDto.setCreateBy(code.getCreateBy());
						codeTransferDto.setCreateDate(code.getCreateDate());
						codeTransferDto.setLocCode(code.getLocCode());
						codeTransferDto.setModifyBy(code.getModifyBy());
						codeTransferDto.setModifyDate(code.getModifyDate());
						codeTransferDto.setNewAgentCode(code.getNewAgentCode());
						codeTransferDto.setOldAgentCode(code.getOldAgentCode());
						codeTransferDto.setPolNum(code.getPolNum());
						codeTransferDto.setPprNum(code.getPprNum());
						codeTransferDto.setReason(code.getReason());
						codeTransferDto.setRequestBy(code.getRequestBy());
						codeTransferDto.setRequestDate(code.getRequestDate());
						codeTransferDto.setSbuCode(code.getSbuCode());
						codeTransferDto.setStatus(code.getStatus());

						codeTransferDtos.add(codeTransferDto);
					}

				});
			}

		}

		return codeTransferDtos;
	}

	@Override
	public List<CodeTransferDto> getPendingCodeTransferPol(String token) throws Exception {
		String usercode = new JwtDecoder().generate(token);
		List<CodeTransferDto> codeTransferDtos = new ArrayList<>();
		if (usercode != null) {
			List<CodeTransferModel> codeTransferModels = codeTransferDao.findByStatusAndCreateBy("PENDING", usercode);

			if (!codeTransferModels.isEmpty()) {
				codeTransferModels.forEach(code -> {
					if (code.getPolNum() != null && code.getPolNum() != "" && !code.getPolNum().isEmpty()) {
						CodeTransferDto codeTransferDto = new CodeTransferDto();
						codeTransferDto.setApprovedBy(code.getApprovedBy());
						codeTransferDto.setApprovedDate(code.getApprovedDate());
						codeTransferDto.setApproverRemark(code.getApproverRemark());
						codeTransferDto.setCodeTransferId(code.getCodeTransferId());
						codeTransferDto.setCreateBy(code.getCreateBy());
						codeTransferDto.setCreateDate(code.getCreateDate());
						codeTransferDto.setLocCode(code.getLocCode());
						codeTransferDto.setModifyBy(code.getModifyBy());
						codeTransferDto.setModifyDate(code.getModifyDate());
						codeTransferDto.setNewAgentCode(code.getNewAgentCode());
						codeTransferDto.setOldAgentCode(code.getOldAgentCode());
						codeTransferDto.setPolNum(code.getPolNum());
						codeTransferDto.setPprNum(code.getPprNum());
						codeTransferDto.setReason(code.getReason());
						codeTransferDto.setRequestBy(code.getRequestBy());
						codeTransferDto.setRequestDate(code.getRequestDate());
						codeTransferDto.setSbuCode(code.getSbuCode());
						codeTransferDto.setStatus(code.getStatus());

						codeTransferDtos.add(codeTransferDto);
					}

				});
			}

		}

		return codeTransferDtos;
	}

	@Override
	public List<CodeTransferDto> getCanceledCodeTransferPrp(String token) throws Exception {
		String usercode = new JwtDecoder().generate(token);
		List<CodeTransferDto> codeTransferDtos = new ArrayList<>();
		if (usercode != null) {
			List<CodeTransferModel> codeTransferModels = codeTransferDao.findByStatusAndCreateBy("CANCELED", usercode);

			if (!codeTransferModels.isEmpty()) {
				codeTransferModels.forEach(code -> {
					if (code.getPolNum() == null || code.getPolNum() == "" || code.getPolNum().isEmpty()) {
						CodeTransferDto codeTransferDto = new CodeTransferDto();
						codeTransferDto.setApprovedBy(code.getApprovedBy());
						codeTransferDto.setApprovedDate(code.getApprovedDate());
						codeTransferDto.setApproverRemark(code.getApproverRemark());
						codeTransferDto.setCodeTransferId(code.getCodeTransferId());
						codeTransferDto.setCreateBy(code.getCreateBy());
						codeTransferDto.setCreateDate(code.getCreateDate());
						codeTransferDto.setLocCode(code.getLocCode());
						codeTransferDto.setModifyBy(code.getModifyBy());
						codeTransferDto.setModifyDate(code.getModifyDate());
						codeTransferDto.setNewAgentCode(code.getNewAgentCode());
						codeTransferDto.setOldAgentCode(code.getOldAgentCode());
						codeTransferDto.setPolNum(code.getPolNum());
						codeTransferDto.setPprNum(code.getPprNum());
						codeTransferDto.setReason(code.getReason());
						codeTransferDto.setRequestBy(code.getRequestBy());
						codeTransferDto.setRequestDate(code.getRequestDate());
						codeTransferDto.setSbuCode(code.getSbuCode());
						codeTransferDto.setStatus(code.getStatus());

						codeTransferDtos.add(codeTransferDto);
					}

				});
			}

		}

		return codeTransferDtos;
	}

	@Override
	public List<CodeTransferDto> getCanceledCodeTransferPol(String token) throws Exception {
		String usercode = new JwtDecoder().generate(token);
		List<CodeTransferDto> codeTransferDtos = new ArrayList<>();
		if (usercode != null) {
			List<CodeTransferModel> codeTransferModels = codeTransferDao.findByStatusAndCreateBy("CANCELED", usercode);

			if (!codeTransferModels.isEmpty()) {
				codeTransferModels.forEach(code -> {
					if (code.getPolNum() != null && code.getPolNum() != "" && !code.getPolNum().isEmpty()) {
						CodeTransferDto codeTransferDto = new CodeTransferDto();
						codeTransferDto.setApprovedBy(code.getApprovedBy());
						codeTransferDto.setApprovedDate(code.getApprovedDate());
						codeTransferDto.setApproverRemark(code.getApproverRemark());
						codeTransferDto.setCodeTransferId(code.getCodeTransferId());
						codeTransferDto.setCreateBy(code.getCreateBy());
						codeTransferDto.setCreateDate(code.getCreateDate());
						codeTransferDto.setLocCode(code.getLocCode());
						codeTransferDto.setModifyBy(code.getModifyBy());
						codeTransferDto.setModifyDate(code.getModifyDate());
						codeTransferDto.setNewAgentCode(code.getNewAgentCode());
						codeTransferDto.setOldAgentCode(code.getOldAgentCode());
						codeTransferDto.setPolNum(code.getPolNum());
						codeTransferDto.setPprNum(code.getPprNum());
						codeTransferDto.setReason(code.getReason());
						codeTransferDto.setRequestBy(code.getRequestBy());
						codeTransferDto.setRequestDate(code.getRequestDate());
						codeTransferDto.setSbuCode(code.getSbuCode());
						codeTransferDto.setStatus(code.getStatus());

						codeTransferDtos.add(codeTransferDto);
					}

				});
			}

		}

		return codeTransferDtos;
	}

	@Override
	public ResponseEntity<Object> saveCodeTransferPrp(SaveCodeTransferDto saveCodeTransferDto) throws Exception {
		ResponseDto dto = null;
		String userCode = new JwtDecoder().generate(saveCodeTransferDto.getToken());

		ArrayList<CodeTransferHelperDto> codeTransferHelperDtos = new ArrayList<>();

		codeTransferHelperDtos = saveCodeTransferDto.getCodeTransferHelpers();

		List<CodeTransferGridDto> codeTransferGrid = new ArrayList<>();

		if (!codeTransferHelperDtos.isEmpty()) {

			for (CodeTransferHelperDto ct : codeTransferHelperDtos) {

				CodeTransferGridDto codeTransferGridDto = new CodeTransferGridDto();

				codeTransferGridDto.setCrAgn(ct.getAgentCode());
				codeTransferGridDto.setCrBranch(ct.getBranch());
				codeTransferGridDto.setNewAgn(saveCodeTransferDto.getAgent());
				codeTransferGridDto.setNewBranch(userDao.getUserLocations(saveCodeTransferDto.getAgent()).get(0));
				codeTransferGridDto.setNo(ct.getPprNum());

				codeTransferGrid.add(codeTransferGridDto);

				CodeTransferModel codeTransferModel = new CodeTransferModel();
				codeTransferModel.setCreateBy(userCode);
				codeTransferModel.setCreateDate(new Date());
				codeTransferModel.setLocCode(ct.getBranch());
				codeTransferModel.setNewAgentCode(saveCodeTransferDto.getAgent());
				codeTransferModel.setOldAgentCode(ct.getAgentCode());
				codeTransferModel.setPprNum(ct.getPprNum());
				codeTransferModel.setReason(saveCodeTransferDto.getReason());
				codeTransferModel.setRequestBy(userCode);
				codeTransferModel.setRequestDate(new Date());
				codeTransferModel.setSbuCode(AppConstant.SBU_CODE);
				codeTransferModel.setStatus("PENDING");

				try {
					InProposalsModel inProposalsModel = inProposalCustomDao
							.getProposalFromPprnum(Integer.valueOf(ct.getPprNum()));

					InPropMedicalReqModel inPropMedicalReqModel = new InPropMedicalReqModel();

					InPropMedicalReqModelPK inPropMedicalReqModelPK = new InPropMedicalReqModelPK();
					inPropMedicalReqModelPK.setInstyp("main");
					inPropMedicalReqModelPK.setLoccod(ct.getBranch());
					inPropMedicalReqModelPK.setMedcod("AD-CT");
					inPropMedicalReqModelPK.setPprnum(Integer.valueOf(ct.getPprNum()));
					inPropMedicalReqModelPK.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
					inPropMedicalReqModelPK.setSbucod(AppConstant.SBU_CODE);

					inPropMedicalReqModel.setInPropMedicalReqModelPK(inPropMedicalReqModelPK);
					inPropMedicalReqModel.setLockin(new Date());
					inPropMedicalReqModel.setTessta("N");
					inPropMedicalReqModel.setHoscod("NA");
					inPropMedicalReqModel.setPaysta("");
					inPropMedicalReqModel.setMedorg("Requested");
					inPropMedicalReqModel.setPayamt(0.00);
					inPropMedicalReqModel.setAddnot("Code Transfer");
					inPropMedicalReqModel.setMednam("Code Transfer");

					inPropMedicalReqDao.save(inPropMedicalReqModel);

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				codeTransferDao.save(codeTransferModel);

			}

			sendMail("prp", saveCodeTransferDto, userCode, codeTransferGrid);

			dto = new ResponseDto();
			dto.setCode("200");
			dto.setStatus("Success");
			dto.setMessage("Code Transfer Request Send Successfully .");
			return new ResponseEntity<>(dto, HttpStatus.OK);

		} else {
			dto = new ResponseDto();
			dto.setCode("204");
			dto.setStatus("Error");
			dto.setMessage("No Proposals To Code Transfer.");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<Object> saveCodeTransferPol(SaveCodeTransferDto saveCodeTransferDto) throws Exception {
		ResponseDto dto = null;
		String userCode = new JwtDecoder().generate(saveCodeTransferDto.getToken());

		ArrayList<CodeTransferHelperDto> codeTransferHelperDtos = new ArrayList<>();

		codeTransferHelperDtos = saveCodeTransferDto.getCodeTransferHelpers();

		List<CodeTransferGridDto> codeTransferGrid = new ArrayList<>();

		if (!codeTransferHelperDtos.isEmpty()) {

			for (CodeTransferHelperDto ct : codeTransferHelperDtos) {

				CodeTransferGridDto codeTransferGridDto = new CodeTransferGridDto();

				codeTransferGridDto.setCrAgn(ct.getAgentCode());
				codeTransferGridDto.setCrBranch(ct.getBranch());
				codeTransferGridDto.setNewAgn(saveCodeTransferDto.getAgent());
				codeTransferGridDto.setNewBranch(userDao.getUserLocations(saveCodeTransferDto.getAgent()).get(0));
				codeTransferGridDto.setNo(ct.getPprNum());

				codeTransferGrid.add(codeTransferGridDto);

				CodeTransferModel codeTransferModel = new CodeTransferModel();
				codeTransferModel.setCreateBy(userCode);
				codeTransferModel.setCreateDate(new Date());
				codeTransferModel.setLocCode(ct.getBranch());
				codeTransferModel.setNewAgentCode(saveCodeTransferDto.getAgent());
				codeTransferModel.setOldAgentCode(ct.getAgentCode());
				codeTransferModel.setPolNum(ct.getPprNum());
				codeTransferModel.setReason(saveCodeTransferDto.getReason());
				codeTransferModel.setRequestBy(userCode);
				codeTransferModel.setRequestDate(new Date());
				codeTransferModel.setSbuCode(AppConstant.SBU_CODE);
				codeTransferModel.setStatus("PENDING");

				try {
					InProposalsModel inProposalsModel = inProposalCustomDao
							.getProposalFromPolnum(Integer.valueOf(ct.getPprNum()));

					codeTransferModel.setPprNum(inProposalsModel.getInProposalsModelPK().getPprnum());

					InPropMedicalReqModel inPropMedicalReqModel = new InPropMedicalReqModel();

					InPropMedicalReqModelPK inPropMedicalReqModelPK = new InPropMedicalReqModelPK();
					inPropMedicalReqModelPK.setInstyp("main");
					inPropMedicalReqModelPK.setLoccod(ct.getBranch());
					inPropMedicalReqModelPK.setMedcod("AD-CT");
					inPropMedicalReqModelPK
							.setPprnum(Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()));
					inPropMedicalReqModelPK.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
					inPropMedicalReqModelPK.setSbucod(AppConstant.SBU_CODE);

					inPropMedicalReqModel.setInPropMedicalReqModelPK(inPropMedicalReqModelPK);
					inPropMedicalReqModel.setLockin(new Date());
					inPropMedicalReqModel.setTessta("N");
					inPropMedicalReqModel.setHoscod("NA");
					inPropMedicalReqModel.setPaysta("");
					inPropMedicalReqModel.setMedorg("Requested");
					inPropMedicalReqModel.setPayamt(0.00);
					inPropMedicalReqModel.setAddnot("Code Transfer");
					inPropMedicalReqModel.setMednam("Code Transfer");

					inPropMedicalReqDao.save(inPropMedicalReqModel);

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				codeTransferDao.save(codeTransferModel);

			}

			sendMail("pol", saveCodeTransferDto, userCode, codeTransferGrid);

			dto = new ResponseDto();
			dto.setCode("200");
			dto.setStatus("Success");
			dto.setMessage("Code Transfer Request Send Successfully .");
			return new ResponseEntity<>(dto, HttpStatus.OK);

		} else {
			dto = new ResponseDto();
			dto.setCode("204");
			dto.setStatus("Error");
			dto.setMessage("No Policy To Code Transfer.");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
	}

	public void sendMail(String type, SaveCodeTransferDto saveCodeTransferDto, String userCode,
			List<CodeTransferGridDto> codeTransferGrid) throws Exception {
		EmailDto emailDto = new EmailDto();

		String toEmail = userDao.getUserEmail(userCode);
		// String toEmail="anjana.t@arpicoinsurance.com";
		String fromEmail = toEmail;

		if (toEmail != null && toEmail != "" && fromEmail != null && fromEmail != "") {
			List<String> ccMails = new ArrayList<>();
			ccMails.add(fromEmail);

			emailDto.setAttachments(new ArrayList<>());
			emailDto.setCcMails(ccMails);
			emailDto.setFromMail(fromEmail);
			emailDto.setToMail(toEmail);
			emailDto.setToken("no token");

			emailDto.setUserCode(userCode);
			// emailDto.setUserCode("anjana.t");
			emailDto.setSubject("Code Transfer Approval ");

			String body = "Dear all, \n\nPlease give your approval for transfer following ";

			if (type.equalsIgnoreCase("pol")) {
				body += "policies under the new agent codes. \n\n";
				body += "| Policy No\t| Curr. Agent\t| Curr. Branch\t| New Agent\t| New Branch\t |\n";
			} else if (type.equalsIgnoreCase("prp")) {
				body += "proposals under the new agent codes. \n\n";
				body += "| Proposal No\t| Curr. Agent\t| Curr. Branch\t| New Agent\t| New Branch\t |\n";
			}

			if (codeTransferGrid != null && !codeTransferGrid.isEmpty()) {
				for (CodeTransferGridDto dto : codeTransferGrid) {
					body += "| " + dto.getNo() + "\t\t| " + dto.getCrAgn() + "\t\t| " + dto.getCrBranch() + "\t\t| "
							+ dto.getNewAgn() + "\t\t| " + dto.getNewBranch() + "\t\t |\n";
				}
			}

			body += "\n\nThanks.\n\n";
			body += "This is system generated email. Do not reply.";

			emailDto.setBody(body);

			if (type.equalsIgnoreCase("pol")) {
				emailDto.setDepartment(AppConstant.EMAIL_DEP_CODE_LIFE_SERVICE);
			} else if (type.equalsIgnoreCase("prp")) {
				emailDto.setDepartment(AppConstant.EMAIL_DEP_CODE_LIFE_NB);
			}

			new InfosysWSClient().sendEmail(emailDto);
		}

	}

	@Override
	public ResponseEntity<Object> rejectCodeTransfer(String user, Integer codeTransferId, String remark)
			throws Exception {
		CodeTransferModel codeTransferModel = codeTransferDao.findOne(codeTransferId);

		ResponseDto dto = null;

		if (codeTransferModel != null) {

			codeTransferModel.setStatus("CANCELED");
			codeTransferModel.setApprovedBy(user);
			codeTransferModel.setApprovedDate(new Date());
			codeTransferModel.setApproverRemark(remark);

			InProposalsModel inProposalsModel = new InProposalsModel();

			if (codeTransferModel.getPolNum() != null && codeTransferModel.getPolNum() != ""
					&& !codeTransferModel.getPolNum().isEmpty()) {

				inProposalsModel = inProposalCustomDao
						.getProposalFromPolnum(Integer.valueOf(codeTransferModel.getPolNum()));
			} else {

				inProposalsModel = inProposalCustomDao
						.getProposalFromPprnum(Integer.valueOf(codeTransferModel.getPprNum()));
			}

			try {
				InPropMedicalReqModel inPropMedicalReqModels = inPropMedicalReqCustomDao.getMedicalReq(
						Integer.valueOf(codeTransferModel.getPprNum()),
						inProposalsModel.getInProposalsModelPK().getPrpseq(), "AD-CT", "N");
				if (inPropMedicalReqModels != null) {
					inPropMedicalReqDao.delete(inPropMedicalReqModels.getInPropMedicalReqModelPK());
					codeTransferDao.save(codeTransferModel);
				}

				dto = new ResponseDto();
				dto.setCode("200");
				dto.setStatus("Success");
				dto.setMessage("Code Transfer Request Reject Successfully .");
				return new ResponseEntity<>(dto, HttpStatus.OK);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		dto = new ResponseDto();
		dto.setCode("204");
		dto.setStatus("Error");
		dto.setMessage("Failed To Reject Code Transfer .");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> approveCodeTransfer(String user, Integer codeTransferId, String remark)
			throws Exception {
		ResponseDto dto = null;

		CodeTransferModel codeTransferModel = codeTransferDao.findOne(codeTransferId);

		if (codeTransferModel != null) {

			codeTransferModel.setStatus("APPROVED");
			codeTransferModel.setApprovedBy(user);
			codeTransferModel.setApprovedDate(new Date());
			codeTransferModel.setApproverRemark(remark);

			InProposalsModel inProposalsModel = new InProposalsModel();

			if (codeTransferModel.getPolNum() != null && codeTransferModel.getPolNum() != ""
					&& !codeTransferModel.getPolNum().isEmpty()) {

				inProposalsModel = inProposalCustomDao
						.getProposalFromPolnum(Integer.valueOf(codeTransferModel.getPolNum()));
			} else {

				inProposalsModel = inProposalCustomDao
						.getProposalFromPprnum(Integer.valueOf(codeTransferModel.getPprNum()));
			}

			Integer pprSeqNew = inProposalsModel.getInProposalsModelPK().getPrpseq() + 1;
			String curPprSta = inProposalsModel.getPprsta();

			List<InPropLoadingModel> inPropLoadingModels = inPropLoadingCustomDao.getPropLoadingBuPprNumAndSeq(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (inPropLoadingModels != null) {
				inPropLoadingModels.forEach(propLoad -> {
					propLoad.getInPropLoadingPK().setPrpseq(pprSeqNew);
				});
			}

			List<InPropAddBenefitModel> addBenefitModels = inPropAddBenefictCustomDao.getBenefByPprSeq(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (addBenefitModels != null) {
				addBenefitModels.forEach(benef -> {
					benef.getInPropAddBenefitPK().setPrpseq(pprSeqNew);
				});
			}

			List<InPropFamDetailsModel> famDetailsModels = famDetailsCustomDao.getFamilyByPprNoAndSeqNo(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (famDetailsModels != null) {
				famDetailsModels.forEach(fam -> {
					fam.getInPropFamDetailsPK().setPrpseq(pprSeqNew);
				});
			}

			List<InPropSchedulesModel> inPropSchedulesModels = propScheduleCustomDao.getScheduleBuPprNoAndSeqNo(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (inPropSchedulesModels != null) {
				inPropSchedulesModels.forEach(sch -> {
					sch.getInPropSchedulesPK().setPrpseq(pprSeqNew);
				});
			}

			List<InPropMedicalReqModel> inPropMedicalReqModels = inPropMedicalReqCustomDao.getMedicalReqByPprNoAndSeq(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (inPropMedicalReqModels != null) {
				inPropMedicalReqModels.forEach(med -> {
					if (med.getInPropMedicalReqModelPK().getMedcod().equals("AD-CT")) {
						med.setTessta("Y");
					}
					med.getInPropMedicalReqModelPK().setPrpseq(pprSeqNew);
				});
			}

			List<InPropSurrenderValsModel> inPropSurrenderValsModels = surrenderValCustomDao
					.getSurrenderValByInpprNoAndSeq(
							Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
							inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (inPropSurrenderValsModels != null) {
				inPropSurrenderValsModels.forEach(sur -> {
					sur.getInPropSurrenderValsPK().setPrpseq(pprSeqNew);
					sur.setAdvcod(codeTransferModel.getNewAgentCode());
				});
			}

			List<InPropNomDetailsModel> nomDetailsModels = propNomDetailsCustomDao.getNomByPprNoAndPprSeq(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (nomDetailsModels != null) {
				nomDetailsModels.forEach(nom -> {
					nom.getInPropNomDetailsModelPK().setPrpseq(pprSeqNew);
				});
			}

			List<InPropPrePolsModel> propPrePolsModels = propPrePolsCustomDao.getPrePolByPprNoAndPprSeq(
					Integer.valueOf(inProposalsModel.getInProposalsModelPK().getPprnum()),
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			if (propPrePolsModels != null) {
				propPrePolsModels.forEach(pre -> {
					pre.getInPropPrePolsModelPK().setPrpseq(pprSeqNew);
				});
			}

			// inactive before row
			inProposalsModel.setPprsta("INAC");
			inProposalDao.save(inProposalsModel);

			// save new row in proposal
			inProposalsModel.setPprsta(curPprSta);
			inProposalsModel.getInProposalsModelPK().setPrpseq(pprSeqNew);
			inProposalsModel.setAdvcod(codeTransferModel.getNewAgentCode());
			inProposalDao.save(inProposalsModel);

			propLoadingDao.save(inPropLoadingModels);
			addBenefictDao.save(addBenefitModels);
			famDetailsDao.save(famDetailsModels);
			propScheduleDao.save(inPropSchedulesModels);
			propMedicalReqDao.save(inPropMedicalReqModels);
			surrenderValDao.save(inPropSurrenderValsModels);
			propNomDetailsDao.save(nomDetailsModels);
			propPrePolsDao.save(propPrePolsModels);

			if (codeTransferModel.getPolNum() == null || codeTransferModel.getPolNum() == ""
					|| codeTransferModel.getPolNum().isEmpty()) {
				List<InTransactionsModel> inTransactionsModels = inTransactionCustomDao
						.getTransactionByPprNum(inProposalsModel.getInProposalsModelPK().getPprnum());
				if (inTransactionsModels != null) {
					inTransactionsModels.forEach(tran -> {
						tran.setAdvcod(codeTransferModel.getNewAgentCode());
					});
				}

				inTransactionsDao.save(inTransactionsModels);

				List<AgentMastModel> agentMastModels = inAgentMastDao
						.getAgentDetails(codeTransferModel.getNewAgentCode());

				if (agentMastModels != null) {
					List<InBillingTransactionsModel> billingTransactionsModels = inBillingTransactionsCustomDao
							.getTransactionsByPprnum(inProposalsModel.getInProposalsModelPK().getPprnum());
					if (billingTransactionsModels != null) {
						billingTransactionsModels.forEach(bill -> {
							bill.setAdvcod(Integer.valueOf(codeTransferModel.getNewAgentCode()));
							bill.setUnlcod(agentMastModels.get(0).getUnlcod());
						});
					}

					inBillingTransactionsDao.save(billingTransactionsModels);
				}

			}

			codeTransferDao.save(codeTransferModel);

			dto = new ResponseDto();
			dto.setCode("200");
			dto.setStatus("Success");
			dto.setMessage("Code Transfer Request Approved Successfully .");
			return new ResponseEntity<>(dto, HttpStatus.OK);

		}

		dto = new ResponseDto();
		dto.setCode("204");
		dto.setStatus("Error");
		dto.setMessage("Failed To Approve Code Transfer .");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@Override
	public List<CodeTransferDto> getCodeTransfersToApprove(String userCode) throws Exception {
//		String userCode=new JwtDecoder().generate(token);
		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);

			System.out.println("calles getCodeTransfersToApprove " + userCode);

			loccodes.forEach(System.out::println);

			List<CodeTransferModel> codeTransferModels = codeTransferDao.findByStatusAndLocCodeIn("PENDING", loccodes);
			List<CodeTransferDto> codeTransferDtos = new ArrayList<>();

			if (!codeTransferModels.isEmpty()) {
				codeTransferModels.forEach(code -> {
					CodeTransferDto codeTransferDto = new CodeTransferDto();
					codeTransferDto.setApprovedBy(code.getApprovedBy());
					codeTransferDto.setApprovedDate(code.getApprovedDate());
					codeTransferDto.setApproverRemark(code.getApproverRemark());
					codeTransferDto.setCodeTransferId(code.getCodeTransferId());
					codeTransferDto.setCreateBy(code.getCreateBy());
					codeTransferDto.setCreateDate(code.getCreateDate());
					codeTransferDto.setLocCode(code.getLocCode());
					codeTransferDto.setModifyBy(code.getModifyBy());
					codeTransferDto.setModifyDate(code.getModifyDate());
					codeTransferDto.setNewAgentCode(code.getNewAgentCode());
					codeTransferDto.setOldAgentCode(code.getOldAgentCode());
					codeTransferDto.setPolNum(code.getPolNum());
					codeTransferDto.setPprNum(code.getPprNum());
					codeTransferDto.setReason(code.getReason());
					codeTransferDto.setRequestBy(code.getRequestBy());
					codeTransferDto.setRequestDate(code.getRequestDate());
					codeTransferDto.setSbuCode(code.getSbuCode());
					codeTransferDto.setStatus(code.getStatus());

					codeTransferDtos.add(codeTransferDto);

				});
			}
			return codeTransferDtos;

		}

		return null;
	}

	@Override
	public List<CodeTransferDto> getPendingCodeTransferPrp(String token, Integer page, Integer offset)
			throws Exception {
		String usercode = new JwtDecoder().generate(token);
		List<CodeTransferDto> codeTransferDtos = new ArrayList<>();
		if (usercode != null) {
			List<CodeTransferModel> codeTransferModels = codeTransferDao.findByStatusAndCreateBy("PENDING", usercode, new PageRequest(page, offset));
			System.out.println(codeTransferModels.size() + "size .. ");
			if (!codeTransferModels.isEmpty()) {
				codeTransferModels.forEach(code -> {
					if (code.getPolNum() == null || code.getPolNum() == "" || code.getPolNum().isEmpty()) {
						CodeTransferDto codeTransferDto = new CodeTransferDto();
						codeTransferDto.setApprovedBy(code.getApprovedBy());
						codeTransferDto.setApprovedDate(code.getApprovedDate());
						codeTransferDto.setApproverRemark(code.getApproverRemark());
						codeTransferDto.setCodeTransferId(code.getCodeTransferId());
						codeTransferDto.setCreateBy(code.getCreateBy());
						codeTransferDto.setCreateDate(code.getCreateDate());
						codeTransferDto.setLocCode(code.getLocCode());
						codeTransferDto.setModifyBy(code.getModifyBy());
						codeTransferDto.setModifyDate(code.getModifyDate());
						codeTransferDto.setNewAgentCode(code.getNewAgentCode());
						codeTransferDto.setOldAgentCode(code.getOldAgentCode());
						codeTransferDto.setPolNum(code.getPolNum());
						codeTransferDto.setPprNum(code.getPprNum());
						codeTransferDto.setReason(code.getReason());
						codeTransferDto.setRequestBy(code.getRequestBy());
						codeTransferDto.setRequestDate(code.getRequestDate());
						codeTransferDto.setSbuCode(code.getSbuCode());
						codeTransferDto.setStatus(code.getStatus());

						codeTransferDtos.add(codeTransferDto);
					}

				});
			}

		}

		return codeTransferDtos;
	}

}
