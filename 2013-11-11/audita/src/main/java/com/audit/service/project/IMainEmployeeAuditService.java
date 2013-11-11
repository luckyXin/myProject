/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.AreaLeaderView;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.Capitalexpenditures;
import com.audit.entity.project.DataPreBaseInfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.IdeaNoteParam;
import com.audit.entity.project.InvestDepartAuditView;
import com.audit.entity.project.LegalDepartAuditView;
import com.audit.entity.project.MainAuditInfo;
import com.audit.entity.project.Problems;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.entity.project.TotalAuditorView;
import com.audit.entity.project.WorkloadInfo;

/**
 * @author User
 * 
 */
public interface IMainEmployeeAuditService {

	/**
	 * 查询需要生成审计通知书的项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param sort
	 * @param order
	 * @param ownerName
	 * @param projectName
	 * @param arrangeType
	 * @param moduleId
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<ArrangeProject> findAdviceNote(int page, int pagesize, String sort, String order,
			String ownerName, String projectName, String arrangeType, String auditType, String moduleId,
			String userAccount, String isUrgent, String timeLimitStart, String timeLimitEnd);

	/**
	 * 查询需要生成意见稿的项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param sort
	 * @param order
	 * @param ownerName
	 * @param projectName
	 * @param arrangeType
	 * @param moduleId
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<ArrangeProject> findIdeaNoteFlg(int page, int pagesize, String sort, String order,
			String ownerName, String projectName, String arrangeType, String auditType, String moduleId,
			String userAccount, String isUrgent, String timeLimitStart, String timeLimitEnd);

	/**
	 * 需要生成审计报告的项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param sort
	 * @param order
	 * @param ownerName
	 * @param projectName
	 * @param arrangeType
	 * @param moduleId
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<ArrangeProject> findReport(int page, int pagesize, String sort, String order,
			String ownerName, String projectName, String arrangeType, String auditType, String moduleId,
			String userAccount, String beginTime, String endTime, String isUrgent, String timeLimitStart,
			String timeLimitEnd);

	/**
	 * 获取预审资料基本信息
	 * 
	 * @param dataPreId
	 * @return
	 */
	public DataPreBaseInfo getDataPreBaseInfo(String dataPreId);

	/**
	 * 获取审计通知书导入的信息
	 * 
	 * @param projectId
	 * @param prospectTime
	 * @param workloadTime
	 * @return
	 */
	public Map<String, String> auditAdviceNot(String projectId, String prospectTime, String workloadTime,
			String adviceNoteType) throws Exception;

	/**
	 * 获取审计信息
	 * 
	 * @param id
	 * @return
	 */
	public MainAuditInfo getMainAuditInfo(String id);

	/**
	 * 添加审计信息
	 */
	public Map<String, Object> addMainAudit(String projectId, String auditReportTime, String auditReportCode,
			String auditReportRemark, String auditReduceAllMoney, String auditInvestAllMoney,
			String auditContentAndScale, String noNormMoney, String otherNoNormMoney, String remark,
			String ooNormProblem, String otherNoNormProblem, String problemRemark, String submitState,
			String userAccount, String projectDirectMoney, String totalInvestmentBudget, String designCompany,
			String projectStartTime, String projectEndTime, String prospectCompany, String projectManageCompany,
			String supervisorCompany, String buildMoney, String alreadyMoney, String progressPayment,
			String designMoney, String prospectMoney, String theTenderFee, String actualCompeleteMoney,
			String beyondMoney, String areaMoney, String cityMoney, String selfRaisedMoney, String bankMoney,
			String otherInputMoney, String projectConsultMoney, String eiaMoney, String clearMoney,
			String projectManageMoney, String auditMoney, String otherOutMoney, String supervisorMoney,
			String dayCount, String money,List<Capitalexpenditures> listCap,List<Problems> listpro
			,String sentAuditMone,String maincontent,String indirectcosts
			) throws Exception;

	/**
	 * 更新审计信息
	 */
	public Map<String, Object> updateMainAudit(String ainAuditId, String projectId, String auditReportTime,
			String auditReportCode, String auditReportRemark, String auditReduceAllMoney, String auditInvestAllMoney,
			String auditContentAndScale, String noNormMoney, String otherNoNormMoney, String remark,
			String noNormProblem, String otherNoNormProblem, String problemRemark, String submitState,
			String userAccount, String projectDirectMoney, String totalInvestmentBudget, String designCompany,
			String projectStartTime, String projectEndTime, String prospectCompany, String projectManageCompany,
			String supervisorCompany, String buildMoney, String alreadyMoney, String progressPayment,
			String designMoney, String prospectMoney, String theTenderFee, String actualCompeleteMoney,
			String beyondMoney, String areaMoney, String cityMoney, String selfRaisedMoney, String bankMoney,
			String otherInputMoney, String projectConsultMoney, String eiaMoney, String clearMoney,
			String projectManageMoney, String auditMoney, String otherOutMoney, String supervisorMoney,
			String dayCount, String money,List<Capitalexpenditures>capitalexpenditures,List<Problems>problems
			,String sentAuditMone,String auditmoney, String maincontent,String indirectcosts,String datapreId
			) throws Exception;

	/**
	 * 获取预审项目基本信息
	 */
	public SectionChiefAuditInfo getProjectMainEmployeeAuditInfo(String projectId);

	/**
	 * 主审第二阶段项目意见稿生成
	 */
	public Map<String, String> updateMainAuditTwo(String projectId, String projectAllInvest, String addProjectApproval,
			String budgetInfo, String budgetInfoFileUrl, String addProjectApprovalFileUrl, String financialRAEUrl,
			String startDate, String endDate, String buildMoney, String reportEndDate, String projectAlreadyExistMoney,
			String projectPlanMoney, String designMoney, String prospectMoney, String agencyInviteBidsMoney,
			String supervisionMoney, String projectManageMoney, String otherMoney, String contractTime,
			String realityTime, String delayTime, String delayReason, String endWorkManyProjectMoney,
			String projectDirectMoney, String indirectMoney, String settleAccountsAuditMoney, String inviteBidsCompany,
			String constructionMoney, String userAccount) throws Exception;

	/**
	 * 主审第二阶段项目意见稿生成
	 */
	public Map<String, Object> updateMainAuditTwo(String projectId, String projectAllInvest, String addProjectApproval,
			String budgetInfo, String budgetInfoFileUrl, String addProjectApprovalFileUrl, String financialRAEUrl,
			String financialRAETime, String budgetUpdateTime, String budgetDirectMoney, String budgetTotalMoney)
			throws Exception;

	/**
	 * 查询打包项目审计整合信息
	 */
	public MainAuditInfo getPackMainAuditInfo(String arrangeId);

	/**
	 * 获取主审第二阶段信息
	 */
	public IdeaNoteParam getIdeaNoteResult(String projectId);

	/**
	 * 主审第一阶段信息更新
	 * 
	 * @param projectId
	 * @param prospectTime
	 * @param workloadTime
	 * @param adviceNoteType
	 * @return
	 */
	public Map<String, Object> updateAdviceNote(String projectId, String prospectTime, String workloadTime,
			String adviceNoteType, String auditNoteRemark, List<FileBelongRelate> file, String[] workloadStartTime,
			String[] workloadEndTime, String[] workloadContext) throws Exception;

	/**
	 * 检查是否可以生成审计报告
	 * 
	 * @param mainAuditId
	 * @param dataPreId
	 * @return
	 */
	public Map<String, Object> checkIsCanCraeteReport(String mainAuditId, String dataPreId);

	/**
	 * 获取审计报告信息
	 * 
	 * @param mainAuditId
	 * @param dataPreId
	 * @return
	 */
	public Map<String, String> getMainAuditReport(String mainAuditId, String dataPreId);

	/**
	 * 获取意见稿类型
	 * 
	 * @param projectId
	 * @return
	 */
	public String getIdeaNoteType(String projectId);

	/**
	 * 判断是否可以创建审计报告
	 * 
	 * @param dataPreId
	 * @return
	 */
	public Map<String, Object> checkIsCanCraeteReport(String dataPreId);

	/**
	 * 获取核对工作量信息
	 */
	public List<WorkloadInfo> getWorkloadInfos(String projectId);

	/**
	 * 查询投资科室已经审签过的项目
	 */
	public GridDataModel<AuditInfo> findInvestAlreadyAudit(Integer page, Integer pagesize, String filed, String order,
			String projectName, String ownerName, String userAccount);

	/**
	 * 投资科室签审意见信息
	 */
	public InvestDepartAuditView getInvestDepartAuditView(String mainAuditId);

	/**
	 * 法制科室签审意见信息
	 */
	public LegalDepartAuditView getLegalDepartAuditView(String mainAuditId);

	/**
	 * 总审计师签审意见信息
	 */
	public TotalAuditorView getTotalAuditorAuditView(String mainAuditId);

	/**
	 * 总审计师签审意见信息
	 */
	public AreaLeaderView getAreaLeaderAuditView(String mainAuditId);

	/**
	 * 添加投资科室审签意见
	 * 
	 * @param mainAuditId
	 * @param investLeaderAuditRemark
	 * @param isInvestLeaderAudit
	 * @param userAccount
	 * @return
	 */
	public Map<String, Object> updateInvestDepartAuditView(String mainAuditId, String investLeaderAuditRemark,
			String isInvestLeaderAudit, String userAccount) throws Exception;

	/**
	 * 添加法制科室审签意见
	 * 
	 * @param mainAuditId
	 * @param investLeaderAuditRemark
	 * @param isInvestLeaderAudit
	 * @param userAccount
	 * @return
	 */
	public Map<String, Object> updateLegalDepartAuditView(String mainAuditId, String legalLeaderAuditRemark,
			String isLegalLeaderAudit, String userAccount) throws Exception;

	/**
	 * 添加总审计师审签意见
	 * 
	 * @param mainAuditId
	 * @param totalAuditTime
	 * @param totalAuditUserAccount
	 * @param totalAuditRemark
	 * @param isTotalAudit
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateTotalAuditorAuditView(String mainAuditId, String totalAuditUserAccount,
			String totalAuditRemark, String isTotalAudit) throws Exception;

	/**
	 * 添加分管领导签审意见
	 * 
	 * @param mainAuditId
	 * @param areaLeaderAuditRemark
	 * @param isLegalLeaderAudit
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateAreaLeaderAuditView(String mainAuditId, String areaLeaderAuditRemark,
			String isAreaLeaderAudit, String userAccount) throws Exception;

	/**
	 * 查询法制科室已经审签过的项目
	 */
	public GridDataModel<AuditInfo> findLegalAlreadyAudit(Integer page, Integer pagesize, String filed, String order,
			String projectName, String ownerName, String userAccount);

	/**
	 * 查询总审计师已经审签过的项目
	 */
	public GridDataModel<AuditInfo> findTotalAuditorAlreadyAudit(Integer page, Integer pagesize, String filed,
			String order, String projectName, String ownerName, String userAccount);

	/**
	 * 查询分管领导已经签审过的项目
	 */
	public GridDataModel<AuditInfo> findAreaLeaderAlreadyAudit(Integer page, Integer pagesize, String filed,
			String order, String projectName, String ownerName, String userAccount);

	/**
	 * 更改审计报告状态切换流程
	 * 
	 * @param mainAuditId
	 */
	public void updateMainAuditReportState(String mainAuditId, String userAccount) throws Exception;
	/**
	 * 
	 * 查询资金支出表
	 * @param xwx
	 * @return lits
	 * @throws Exception
	 */
	public List<Capitalexpenditures> selectCapitalexpenditures(String mainAuditId) throws Exception;
	
	/**
	 * 
	 * xwx
	 * list 
	 * 查询问题 表
	 * 
	 */
	public List<Problems> getProblems(String mainAuditId) throws Exception;
}
